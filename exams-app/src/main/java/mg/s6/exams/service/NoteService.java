package mg.s6.exams.service;

import mg.s6.exams.entity.*;
import mg.s6.exams.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final ParametreRepository parametreRepository;
    private final CandidatRepository candidatRepository;
    private final MatiereRepository matiereRepository;

    public NoteService(NoteRepository noteRepository,
                       ParametreRepository parametreRepository,
                       CandidatRepository candidatRepository,
                       MatiereRepository matiereRepository) {
        this.noteRepository = noteRepository;
        this.parametreRepository = parametreRepository;
        this.candidatRepository = candidatRepository;
        this.matiereRepository = matiereRepository;
    }

    /**
     * Calcule la note finale d'un candidat dans une matiere.
     *
     * Algorithme :
     * 1. Recuperer toutes les notes du candidat dans la matiere.
     * 2. Si toutes les notes sont egales => retourner cette note.
     * 3. Sinon calculer d = somme des |ni - nj| pour toutes les paires (i < j).
     * 4. Chercher dans Parametre les parametres de la matiere dont l'operateur
     *    et le seuil correspondent a d. Si plusieurs correspondent, choisir celui
     *    dont la difference de seuil est la plus proche de d. Si egalite, choisir
     *    celui avec le seuil le plus petit.
     * 5. Appliquer la resolution : plus grand, plus petit ou moyenne.
     */
    public BigDecimal calculerNoteFinale(Long idCandidat, Long idMatiere) {
        Candidat candidat = candidatRepository.findById(idCandidat)
                .orElseThrow(() -> new IllegalArgumentException("Candidat introuvable: " + idCandidat));
        Matiere matiere = matiereRepository.findById(idMatiere)
                .orElseThrow(() -> new IllegalArgumentException("Matiere introuvable: " + idMatiere));

        List<Note> notes = noteRepository.findByCandidatAndMatiere(candidat, matiere);

        if (notes.isEmpty()) {
            throw new IllegalStateException("Aucune note trouvee pour ce candidat dans cette matiere.");
        }

        // Verifier si toutes les notes sont egales
        BigDecimal premiere = notes.get(0).getValeurNote();
        boolean toutesEgales = notes.stream()
                .allMatch(n -> n.getValeurNote().compareTo(premiere) == 0);

        if (toutesEgales) {
            return premiere;
        }

        // Calculer d = somme des differences absolues 2 a 2
        BigDecimal d = BigDecimal.ZERO;
        for (int i = 0; i < notes.size(); i++) {
            for (int j = i + 1; j < notes.size(); j++) {
                BigDecimal diff = notes.get(i).getValeurNote()
                        .subtract(notes.get(j).getValeurNote())
                        .abs();
                d = d.add(diff);
            }
        }

        // Trouver le parametre applicable en utilisant la nouvelle methode
        List<Parametre> parametres = parametreRepository.findByMatiere(matiere);
        Parametre parametreApplicable = trouverParametreApplicable(d, parametres);

        if (parametreApplicable == null) {
            // Aucun parametre ne correspond => retourner la moyenne par defaut
            return calculerMoyenne(notes);
        }

        String resolution = parametreApplicable.getResolution().getNom();
        return switch (resolution) {
            case "plus grand" -> notes.stream()
                    .map(Note::getValeurNote)
                    .max(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);
            case "plus petit" -> notes.stream()
                    .map(Note::getValeurNote)
                    .min(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);
            default -> calculerMoyenne(notes);
        };
    }

    /**
     * Trouve le parametre applicable parmi la liste des parametres.
     * 
     * Si plusieurs parametres correspondent a la valeur d :
     * 1. Si l'un des parametres a une difference de seuil plus proche de d, 
     *    alors on selectionne celui-ci.
     * 2. Si les distances entre d et les differences seuil sont egales, 
     *    alors on selectionne celui avec la difference seuil la plus petite.
     *
     * @param d la valeur de la difference calculee
     * @param parametres la liste des parametres de la matiere
     * @return le parametre applicable, ou null si aucun ne correspond
     */
    private Parametre trouverParametreApplicable(BigDecimal d, List<Parametre> parametres) {
        // Rechercher tous les parametres qui correspondent a d
        List<Parametre> parametresCorrespondants = new ArrayList<>();
        
        for (Parametre p : parametres) {
            String op = p.getOperateur().getOperateur();
            BigDecimal seuil = p.getDifference();
            boolean condition = switch (op) {
                case ">"  -> d.compareTo(seuil) > 0;
                case ">=" -> d.compareTo(seuil) >= 0;
                case "<"  -> d.compareTo(seuil) < 0;
                case "<=" -> d.compareTo(seuil) <= 0;
                default   -> throw new IllegalStateException("Operateur inconnu: " + op);
            };
            if (condition) {
                parametresCorrespondants.add(p);
            }
        }
        
        // Si aucun parametre ne correspond, retourner null
        if (parametresCorrespondants.isEmpty()) {
            return null;
        }
        
        // Si un seul parametre correspond, le retourner
        if (parametresCorrespondants.size() == 1) {
            return parametresCorrespondants.get(0);
        }
        
        // Si plusieurs parametres correspondent, appliquer la logique de selection
        // Reduire la liste en gardant celui avec la difference de seuil la plus proche de d
        // En cas d'egalite, garder celui avec le seuil le plus petit
        Parametre parametreSelectionne = parametresCorrespondants.get(0);
        BigDecimal minDiff = d.subtract(parametreSelectionne.getDifference()).abs();
        
        for (int i = 1; i < parametresCorrespondants.size(); i++) {
            Parametre p = parametresCorrespondants.get(i);
            BigDecimal diffActuelle = d.subtract(p.getDifference()).abs();
            int comparaisonDiff = diffActuelle.compareTo(minDiff);
            
            if (comparaisonDiff < 0) {
                // La difference est plus petite, donc plus proche de d
                parametreSelectionne = p;
                minDiff = diffActuelle;
            } else if (comparaisonDiff == 0) {
                // Les differences sont egales, garder celui avec le seuil le plus petit
                if (p.getDifference().compareTo(parametreSelectionne.getDifference()) < 0) {
                    parametreSelectionne = p;
                }
            }
        }
        
        return parametreSelectionne;
    }

    private BigDecimal calculerMoyenne(List<Note> notes) {
        BigDecimal somme = notes.stream()
                .map(Note::getValeurNote)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return somme.divide(BigDecimal.valueOf(notes.size()), 2, RoundingMode.HALF_UP);
    }

    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }
}

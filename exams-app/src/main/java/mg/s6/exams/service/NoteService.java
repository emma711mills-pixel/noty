package mg.s6.exams.service;

import mg.s6.exams.entity.*;
import mg.s6.exams.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
     * 4. Chercher dans Parametre le parametre de la matiere dont l'operateur
     *    et le seuil correspondent a d (ex: d > 3).
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

        // Trouver le parametre applicable
        List<Parametre> parametres = parametreRepository.findByMatiere(matiere);
        Parametre parametreApplicable = null;
        for (Parametre p : parametres) {
            String op = p.getOperateur().getOperateur();
            BigDecimal seuil = p.getDifference();
            boolean condition = ">".equals(op)
                    ? d.compareTo(seuil) > 0
                    : d.compareTo(seuil) < 0;
            if (condition) {
                parametreApplicable = p;
                break;
            }
        }

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

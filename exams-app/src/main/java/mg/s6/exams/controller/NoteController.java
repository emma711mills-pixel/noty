package mg.s6.exams.controller;

import mg.s6.exams.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("candidats", noteService.getAllCandidats());
        model.addAttribute("matieres", noteService.getAllMatieres());
        return "index";
    }

    @PostMapping("/calculer")
    public String calculer(@RequestParam Long idCandidat,
                           @RequestParam Long idMatiere,
                           Model model) {
        model.addAttribute("candidats", noteService.getAllCandidats());
        model.addAttribute("matieres", noteService.getAllMatieres());
        model.addAttribute("idCandidatSelected", idCandidat);
        model.addAttribute("idMatiereSelected", idMatiere);

        try {
            BigDecimal noteFinale = noteService.calculerNoteFinale(idCandidat, idMatiere);
            model.addAttribute("noteFinale", noteFinale);
        } catch (Exception e) {
            model.addAttribute("erreur", e.getMessage());
        }

        return "index";
    }
}

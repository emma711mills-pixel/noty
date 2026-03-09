package mg.s6.exams.controller;

import mg.s6.exams.entity.Candidat;
import mg.s6.exams.service.CandidatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/candidats")
public class CandidatWebController {

    private final CandidatService candidatService;

    public CandidatWebController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("candidats", candidatService.findAll());
        return "candidat/list";
    }

    @GetMapping("/nouveau")
    public String newForm(Model model) {
        model.addAttribute("candidat", new Candidat());
        return "candidat/form";
    }

    @PostMapping("/nouveau")
    public String create(@ModelAttribute Candidat candidat) {
        candidatService.save(candidat);
        return "redirect:/candidats";
    }

    @GetMapping("/modifier/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return candidatService.findById(id).map(c -> {
            model.addAttribute("candidat", c);
            return "candidat/form";
        }).orElse("redirect:/candidats");
    }

    @PostMapping("/modifier/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Candidat candidat) {
        candidatService.update(id, candidat);
        return "redirect:/candidats";
    }

    @GetMapping("/supprimer/{id}")
    public String delete(@PathVariable Long id) {
        candidatService.deleteById(id);
        return "redirect:/candidats";
    }
}

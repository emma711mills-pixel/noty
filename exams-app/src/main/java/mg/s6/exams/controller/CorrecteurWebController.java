package mg.s6.exams.controller;

import mg.s6.exams.entity.Correcteur;
import mg.s6.exams.service.CorrecteurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/correcteurs")
public class CorrecteurWebController {

    private final CorrecteurService correcteurService;

    public CorrecteurWebController(CorrecteurService correcteurService) {
        this.correcteurService = correcteurService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("correcteurs", correcteurService.findAll());
        return "correcteur/list";
    }

    @GetMapping("/nouveau")
    public String newForm(Model model) {
        model.addAttribute("correcteur", new Correcteur());
        return "correcteur/form";
    }

    @PostMapping("/nouveau")
    public String create(@ModelAttribute Correcteur correcteur) {
        correcteurService.save(correcteur);
        return "redirect:/correcteurs";
    }

    @GetMapping("/modifier/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return correcteurService.findById(id).map(c -> {
            model.addAttribute("correcteur", c);
            return "correcteur/form";
        }).orElse("redirect:/correcteurs");
    }

    @PostMapping("/modifier/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Correcteur correcteur) {
        correcteurService.update(id, correcteur);
        return "redirect:/correcteurs";
    }

    @GetMapping("/supprimer/{id}")
    public String delete(@PathVariable Long id) {
        correcteurService.deleteById(id);
        return "redirect:/correcteurs";
    }
}

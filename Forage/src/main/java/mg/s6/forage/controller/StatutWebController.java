package mg.s6.forage.controller;

import mg.s6.forage.entity.Statut;
import mg.s6.forage.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/statuts")
public class StatutWebController {

    @Autowired
    private StatutService statutService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("statuts", statutService.findAll());
        return "statut/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("statut", new Statut());
        model.addAttribute("action", "/statuts/save");
        return "statut/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Statut statut) {
        statutService.save(statut);
        return "redirect:/statuts";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return statutService.findById(id)
                .map(statut -> {
                    model.addAttribute("statut", statut);
                    model.addAttribute("action", "/statuts/update/" + id);
                    return "statut/form";
                })
                .orElse("redirect:/statuts");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Statut statut) {
        statutService.update(id, statut);
        return "redirect:/statuts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        statutService.delete(id);
        return "redirect:/statuts";
    }
}

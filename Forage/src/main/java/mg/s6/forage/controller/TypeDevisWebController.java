package mg.s6.forage.controller;

import mg.s6.forage.entity.TypeDevis;
import mg.s6.forage.service.TypeDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/typeDevis")
public class TypeDevisWebController {

    @Autowired
    private TypeDevisService typeDevisService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("typeDevisList", typeDevisService.findAll());
        return "typeDevis/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("typeDevis", new TypeDevis());
        model.addAttribute("action", "/typeDevis/save");
        return "typeDevis/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute TypeDevis typeDevis) {
        typeDevisService.save(typeDevis);
        return "redirect:/typeDevis";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return typeDevisService.findById(id)
                .map(typeDevis -> {
                    model.addAttribute("typeDevis", typeDevis);
                    model.addAttribute("action", "/typeDevis/update/" + id);
                    return "typeDevis/form";
                })
                .orElse("redirect:/typeDevis");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute TypeDevis typeDevis) {
        typeDevisService.update(id, typeDevis);
        return "redirect:/typeDevis";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        typeDevisService.delete(id);
        return "redirect:/typeDevis";
    }
}

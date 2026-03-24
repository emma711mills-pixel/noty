package mg.s6.forage.controller;

import mg.s6.forage.entity.DetailDevis;
import mg.s6.forage.service.DetailDevisService;
import mg.s6.forage.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/detailDevis")
public class DetailDevisWebController {

    @Autowired
    private DetailDevisService detailDevisService;
    
    @Autowired
    private DevisService devisService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("detailDevisList", detailDevisService.findAll());
        return "detailDevis/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("detailDevis", new DetailDevis());
        model.addAttribute("devisList", devisService.findAll());
        model.addAttribute("action", "/detailDevis/save");
        return "detailDevis/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute DetailDevis detailDevis, @RequestParam Long idDevis) {
        detailDevis.setDevis(devisService.findById(idDevis).orElse(null));
        if (detailDevis.getMontant() == null) {
            detailDevis.setMontant(BigDecimal.ZERO);
        }
        detailDevisService.save(detailDevis);
        return "redirect:/detailDevis";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return detailDevisService.findById(id)
                .map(detailDevis -> {
                    model.addAttribute("detailDevis", detailDevis);
                    model.addAttribute("devisList", devisService.findAll());
                    model.addAttribute("selectedDevis", detailDevis.getDevis() != null ? detailDevis.getDevis().getId() : null);
                    model.addAttribute("action", "/detailDevis/update/" + id);
                    return "detailDevis/form";
                })
                .orElse("redirect:/detailDevis");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute DetailDevis detailDevis, @RequestParam Long idDevis) {
        detailDevis.setDevis(devisService.findById(idDevis).orElse(null));
        detailDevisService.update(id, detailDevis);
        return "redirect:/detailDevis";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        detailDevisService.delete(id);
        return "redirect:/detailDevis";
    }
}

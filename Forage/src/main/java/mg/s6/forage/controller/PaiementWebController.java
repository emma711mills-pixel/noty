package mg.s6.forage.controller;

import mg.s6.forage.entity.Paiement;
import mg.s6.forage.service.PaiementService;
import mg.s6.forage.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/paiements")
public class PaiementWebController {

    @Autowired
    private PaiementService paiementService;
    
    @Autowired
    private DevisService devisService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("paiements", paiementService.findAll());
        return "paiement/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("paiement", new Paiement());
        model.addAttribute("devisList", devisService.findAll());
        model.addAttribute("action", "/paiements/save");
        return "paiement/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Paiement paiement, @RequestParam Long idDevis) {
        paiement.setDevis(devisService.findById(idDevis).orElse(null));
        if (paiement.getDatePaiement() == null) {
            paiement.setDatePaiement(LocalDateTime.now());
        }
        if (paiement.getMontant() == null) {
            paiement.setMontant(BigDecimal.ZERO);
        }
        paiementService.save(paiement);
        return "redirect:/paiements";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return paiementService.findById(id)
                .map(paiement -> {
                    model.addAttribute("paiement", paiement);
                    model.addAttribute("devisList", devisService.findAll());
                    model.addAttribute("selectedDevis", paiement.getDevis() != null ? paiement.getDevis().getId() : null);
                    model.addAttribute("action", "/paiements/update/" + id);
                    return "paiement/form";
                })
                .orElse("redirect:/paiements");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Paiement paiement, @RequestParam Long idDevis) {
        paiement.setDevis(devisService.findById(idDevis).orElse(null));
        paiementService.update(id, paiement);
        return "redirect:/paiements";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        paiementService.delete(id);
        return "redirect:/paiements";
    }
}

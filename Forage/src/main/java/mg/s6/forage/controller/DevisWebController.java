package mg.s6.forage.controller;

import mg.s6.forage.entity.Devis;
import mg.s6.forage.service.DevisService;
import mg.s6.forage.service.DemandeService;
import mg.s6.forage.service.TypeDevisService;
import mg.s6.forage.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/devis")
public class DevisWebController {

    @Autowired
    private DevisService devisService;
    
    @Autowired
    private DemandeService demandeService;
    
    @Autowired
    private TypeDevisService typeDevisService;
    
    @Autowired
    private StatutService statutService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("devis", devisService.findAll());
        return "devis/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("devis", new Devis());
        model.addAttribute("demandes", demandeService.findAll());
        model.addAttribute("typeDevisList", typeDevisService.findAll());
        model.addAttribute("statuts", statutService.findAll());
        model.addAttribute("action", "/devis/save");
        return "devis/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Devis devis,
                       @RequestParam Long idDemande,
                       @RequestParam Long idTypeDevis,
                       @RequestParam Long idStatut) {
        devis.setDemande(demandeService.findById(idDemande).orElse(null));
        devis.setTypeDevis(typeDevisService.findById(idTypeDevis).orElse(null));
        devis.setStatut(statutService.findById(idStatut).orElse(null));
        if (devis.getDateDevis() == null) {
            devis.setDateDevis(LocalDateTime.now());
        }
        if (devis.getMontantTotal() == null) {
            devis.setMontantTotal(BigDecimal.ZERO);
        }
        if (devis.getStatutPaiement() == null) {
            devis.setStatutPaiement("En attente");
        }
        devisService.save(devis);
        return "redirect:/devis";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return devisService.findById(id)
                .map(devis -> {
                    model.addAttribute("devis", devis);
                    model.addAttribute("demandes", demandeService.findAll());
                    model.addAttribute("typeDevisList", typeDevisService.findAll());
                    model.addAttribute("statuts", statutService.findAll());
                    model.addAttribute("selectedDemande", devis.getDemande() != null ? devis.getDemande().getId() : null);
                    model.addAttribute("selectedTypeDevis", devis.getTypeDevis() != null ? devis.getTypeDevis().getId() : null);
                    model.addAttribute("selectedStatut", devis.getStatut() != null ? devis.getStatut().getId() : null);
                    model.addAttribute("action", "/devis/update/" + id);
                    return "devis/form";
                })
                .orElse("redirect:/devis");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Devis devis,
                        @RequestParam Long idDemande,
                        @RequestParam Long idTypeDevis,
                        @RequestParam Long idStatut) {
        devis.setDemande(demandeService.findById(idDemande).orElse(null));
        devis.setTypeDevis(typeDevisService.findById(idTypeDevis).orElse(null));
        devis.setStatut(statutService.findById(idStatut).orElse(null));
        devisService.update(id, devis);
        return "redirect:/devis";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        devisService.delete(id);
        return "redirect:/devis";
    }
}

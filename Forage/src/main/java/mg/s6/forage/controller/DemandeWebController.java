package mg.s6.forage.controller;

import mg.s6.forage.entity.Demande;
import mg.s6.forage.service.DemandeService;
import mg.s6.forage.service.ClientService;
import mg.s6.forage.service.CommuneService;
import mg.s6.forage.service.DemandeStatutService;
import mg.s6.forage.service.StatutService;
import mg.s6.forage.entity.DemandeStatut;
import mg.s6.forage.entity.Statut;
import mg.s6.forage.repository.DemandeSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/demandes")
public class DemandeWebController {

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private DemandeStatutService demandeStatutService;

    @Autowired
    private StatutService statutService;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private CommuneService communeService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("demandes", demandeService.findAllWithLatestStatus());
        return "demande/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("demande", new Demande());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("communes", communeService.findAll());
        model.addAttribute("action", "/demandes/save");
        return "demande/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Demande demande, 
                       @RequestParam Long idClient,
                       @RequestParam Long idCommune) {
        demande.setClient(clientService.findById(idClient).orElse(null));
        demande.setCommune(communeService.findById(idCommune).orElse(null));
        if (demande.getDateDemande() == null) {
            demande.setDateDemande(LocalDateTime.now());
        }
        demandeService.save(demande);
        return "redirect:/demandes";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return demandeService.findById(id)
                .map(demande -> {
                    model.addAttribute("demande", demande);
                    model.addAttribute("clients", clientService.findAll());
                    model.addAttribute("communes", communeService.findAll());
                    model.addAttribute("selectedClient", demande.getClient() != null ? demande.getClient().getId() : null);
                    model.addAttribute("selectedCommune", demande.getCommune() != null ? demande.getCommune().getId() : null);
                    model.addAttribute("action", "/demandes/update/" + id);
                    return "demande/form";
                })
                .orElse("redirect:/demandes");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Demande demande,
                        @RequestParam Long idClient,
                        @RequestParam Long idCommune) {
        demande.setClient(clientService.findById(idClient).orElse(null));
        demande.setCommune(communeService.findById(idCommune).orElse(null));
        demandeService.update(id, demande);
        return "redirect:/demandes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        demandeService.delete(id);
        return "redirect:/demandes";
    }

    @GetMapping("/validate/{id}")
    public String validate(@PathVariable Long id) {
        Optional<Demande> demandeOpt = demandeService.findById(id);
        if (demandeOpt.isPresent()) {
            Demande demande = demandeOpt.get();
            Optional<Statut> statutOpt = statutService.findById(2L); // DEMANDE_VALIDEE
            if (statutOpt.isPresent()) {
                DemandeStatut demandeStatut = new DemandeStatut(demande, statutOpt.get());
                demandeStatutService.save(demandeStatut);
            }
        }
        return "redirect:/demandes";
    }
}

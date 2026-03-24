package mg.s6.forage.controller;

import mg.s6.forage.entity.Demande;
import mg.s6.forage.service.DemandeService;
import mg.s6.forage.service.ClientService;
import mg.s6.forage.service.RegionService;
import mg.s6.forage.service.DistrictService;
import mg.s6.forage.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/demandes")
public class DemandeWebController {

    @Autowired
    private DemandeService demandeService;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private RegionService regionService;
    
    @Autowired
    private DistrictService districtService;
    
    @Autowired
    private CommuneService communeService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("demandes", demandeService.findAll());
        return "demande/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("demande", new Demande());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("districts", districtService.findAll());
        model.addAttribute("communes", communeService.findAll());
        model.addAttribute("action", "/demandes/save");
        return "demande/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Demande demande, 
                       @RequestParam Long idClient,
                       @RequestParam Long idRegion,
                       @RequestParam Long idDistrict,
                       @RequestParam Long idCommune) {
        demande.setClient(clientService.findById(idClient).orElse(null));
        demande.setRegion(regionService.findById(idRegion).orElse(null));
        demande.setDistrict(districtService.findById(idDistrict).orElse(null));
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
                    model.addAttribute("regions", regionService.findAll());
                    model.addAttribute("districts", districtService.findAll());
                    model.addAttribute("communes", communeService.findAll());
                    model.addAttribute("selectedClient", demande.getClient() != null ? demande.getClient().getId() : null);
                    model.addAttribute("selectedRegion", demande.getRegion() != null ? demande.getRegion().getId() : null);
                    model.addAttribute("selectedDistrict", demande.getDistrict() != null ? demande.getDistrict().getId() : null);
                    model.addAttribute("selectedCommune", demande.getCommune() != null ? demande.getCommune().getId() : null);
                    model.addAttribute("action", "/demandes/update/" + id);
                    return "demande/form";
                })
                .orElse("redirect:/demandes");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Demande demande,
                        @RequestParam Long idClient,
                        @RequestParam Long idRegion,
                        @RequestParam Long idDistrict,
                        @RequestParam Long idCommune) {
        demande.setClient(clientService.findById(idClient).orElse(null));
        demande.setRegion(regionService.findById(idRegion).orElse(null));
        demande.setDistrict(districtService.findById(idDistrict).orElse(null));
        demande.setCommune(communeService.findById(idCommune).orElse(null));
        demandeService.update(id, demande);
        return "redirect:/demandes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        demandeService.delete(id);
        return "redirect:/demandes";
    }
}

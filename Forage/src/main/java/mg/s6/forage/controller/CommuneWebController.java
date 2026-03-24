package mg.s6.forage.controller;

import mg.s6.forage.entity.Commune;
import mg.s6.forage.service.CommuneService;
import mg.s6.forage.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/communes")
public class CommuneWebController {

    @Autowired
    private CommuneService communeService;
    
    @Autowired
    private DistrictService districtService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("communes", communeService.findAll());
        return "commune/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("commune", new Commune());
        model.addAttribute("districts", districtService.findAll());
        model.addAttribute("action", "/communes/save");
        return "commune/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Commune commune, @RequestParam Long idDistrict) {
        commune.setDistrict(districtService.findById(idDistrict).orElse(null));
        communeService.save(commune);
        return "redirect:/communes";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return communeService.findById(id)
                .map(commune -> {
                    model.addAttribute("commune", commune);
                    model.addAttribute("districts", districtService.findAll());
                    model.addAttribute("selectedDistrict", commune.getDistrict() != null ? commune.getDistrict().getId() : null);
                    model.addAttribute("action", "/communes/update/" + id);
                    return "commune/form";
                })
                .orElse("redirect:/communes");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Commune commune, @RequestParam Long idDistrict) {
        commune.setDistrict(districtService.findById(idDistrict).orElse(null));
        communeService.update(id, commune);
        return "redirect:/communes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        communeService.delete(id);
        return "redirect:/communes";
    }
}

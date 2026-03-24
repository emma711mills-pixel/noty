package mg.s6.forage.controller;

import mg.s6.forage.entity.District;
import mg.s6.forage.service.DistrictService;
import mg.s6.forage.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/districts")
public class DistrictWebController {

    @Autowired
    private DistrictService districtService;
    
    @Autowired
    private RegionService regionService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("districts", districtService.findAll());
        return "district/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("district", new District());
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("action", "/districts/save");
        return "district/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute District district, @RequestParam Long idRegion) {
        district.setRegion(regionService.findById(idRegion).orElse(null));
        districtService.save(district);
        return "redirect:/districts";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return districtService.findById(id)
                .map(district -> {
                    model.addAttribute("district", district);
                    model.addAttribute("regions", regionService.findAll());
                    model.addAttribute("selectedRegion", district.getRegion() != null ? district.getRegion().getId() : null);
                    model.addAttribute("action", "/districts/update/" + id);
                    return "district/form";
                })
                .orElse("redirect:/districts");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute District district, @RequestParam Long idRegion) {
        district.setRegion(regionService.findById(idRegion).orElse(null));
        districtService.update(id, district);
        return "redirect:/districts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        districtService.delete(id);
        return "redirect:/districts";
    }
}

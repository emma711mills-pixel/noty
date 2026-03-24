package mg.s6.forage.controller;

import mg.s6.forage.entity.Region;
import mg.s6.forage.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/regions")
public class RegionWebController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("regions", regionService.findAll());
        return "region/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("region", new Region());
        model.addAttribute("action", "/regions/save");
        return "region/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Region region) {
        regionService.save(region);
        return "redirect:/regions";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return regionService.findById(id)
                .map(region -> {
                    model.addAttribute("region", region);
                    model.addAttribute("action", "/regions/update/" + id);
                    return "region/form";
                })
                .orElse("redirect:/regions");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Region region) {
        regionService.update(id, region);
        return "redirect:/regions";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        regionService.delete(id);
        return "redirect:/regions";
    }
}

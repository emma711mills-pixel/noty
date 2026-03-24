package mg.s6.forage.controller;

import mg.s6.forage.entity.Client;
import mg.s6.forage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientWebController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "client/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("action", "/clients/save");
        return "client/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return clientService.findById(id)
                .map(client -> {
                    model.addAttribute("client", client);
                    model.addAttribute("action", "/clients/update/" + id);
                    return "client/form";
                })
                .orElse("redirect:/clients");
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Client client) {
        clientService.update(id, client);
        return "redirect:/clients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientService.delete(id);
        return "redirect:/clients";
    }
}

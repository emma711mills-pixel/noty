package mg.s6.forage.controller;

import mg.s6.forage.entity.Commune;
import mg.s6.forage.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communes")
public class CommuneController {

    @Autowired
    private CommuneService communeService;

    @GetMapping
    public List<Commune> getAllCommunes() {
        return communeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commune> getCommuneById(@PathVariable Long id) {
        return communeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Commune createCommune(@RequestBody Commune commune) {
        return communeService.save(commune);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commune> updateCommune(@PathVariable Long id, @RequestBody Commune communeDetails) {
        Commune updatedCommune = communeService.update(id, communeDetails);
        if (updatedCommune != null) {
            return ResponseEntity.ok(updatedCommune);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommune(@PathVariable Long id) {
        communeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
package mg.s6.forage.controller;

import mg.s6.forage.entity.Statut;
import mg.s6.forage.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuts")
public class StatutController {

    @Autowired
    private StatutService statutService;

    @GetMapping
    public List<Statut> getAllStatuts() {
        return statutService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statut> getStatutById(@PathVariable Long id) {
        return statutService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Statut createStatut(@RequestBody Statut statut) {
        return statutService.save(statut);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Statut> updateStatut(@PathVariable Long id, @RequestBody Statut statutDetails) {
        Statut updatedStatut = statutService.update(id, statutDetails);
        if (updatedStatut != null) {
            return ResponseEntity.ok(updatedStatut);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatut(@PathVariable Long id) {
        statutService.delete(id);
        return ResponseEntity.ok().build();
    }
}
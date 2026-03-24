package mg.s6.forage.controller;

import mg.s6.forage.entity.DetailDevis;
import mg.s6.forage.service.DetailDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/details-devis")
public class DetailDevisController {

    @Autowired
    private DetailDevisService detailDevisService;

    @GetMapping
    public List<DetailDevis> getAllDetailsDevis() {
        return detailDevisService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailDevis> getDetailDevisById(@PathVariable Long id) {
        return detailDevisService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetailDevis createDetailDevis(@RequestBody DetailDevis detailDevis) {
        return detailDevisService.save(detailDevis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailDevis> updateDetailDevis(@PathVariable Long id, @RequestBody DetailDevis detailDevisDetails) {
        DetailDevis updatedDetailDevis = detailDevisService.update(id, detailDevisDetails);
        if (updatedDetailDevis != null) {
            return ResponseEntity.ok(updatedDetailDevis);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailDevis(@PathVariable Long id) {
        detailDevisService.delete(id);
        return ResponseEntity.ok().build();
    }
}
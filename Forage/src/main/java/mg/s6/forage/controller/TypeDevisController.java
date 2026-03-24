package mg.s6.forage.controller;

import mg.s6.forage.entity.TypeDevis;
import mg.s6.forage.service.TypeDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types-devis")
public class TypeDevisController {

    @Autowired
    private TypeDevisService typeDevisService;

    @GetMapping
    public List<TypeDevis> getAllTypesDevis() {
        return typeDevisService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDevis> getTypeDevisById(@PathVariable Long id) {
        return typeDevisService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TypeDevis createTypeDevis(@RequestBody TypeDevis typeDevis) {
        return typeDevisService.save(typeDevis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeDevis> updateTypeDevis(@PathVariable Long id, @RequestBody TypeDevis typeDevisDetails) {
        TypeDevis updatedTypeDevis = typeDevisService.update(id, typeDevisDetails);
        if (updatedTypeDevis != null) {
            return ResponseEntity.ok(updatedTypeDevis);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeDevis(@PathVariable Long id) {
        typeDevisService.delete(id);
        return ResponseEntity.ok().build();
    }
}
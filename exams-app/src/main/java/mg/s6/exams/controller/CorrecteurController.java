package mg.s6.exams.controller;

import mg.s6.exams.entity.Correcteur;
import mg.s6.exams.service.CorrecteurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/correcteurs")
public class CorrecteurController {

    private final CorrecteurService correcteurService;

    public CorrecteurController(CorrecteurService correcteurService) {
        this.correcteurService = correcteurService;
    }

    // GET /api/correcteurs
    @GetMapping
    public List<Correcteur> getAll() {
        return correcteurService.findAll();
    }

    // GET /api/correcteurs/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Correcteur> getById(@PathVariable Long id) {
        return correcteurService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/correcteurs
    @PostMapping
    public Correcteur create(@RequestBody Correcteur correcteur) {
        return correcteurService.save(correcteur);
    }

    // PUT /api/correcteurs/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Correcteur> update(@PathVariable Long id, @RequestBody Correcteur correcteur) {
        if (correcteurService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(correcteurService.update(id, correcteur));
    }

    // DELETE /api/correcteurs/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (correcteurService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        correcteurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

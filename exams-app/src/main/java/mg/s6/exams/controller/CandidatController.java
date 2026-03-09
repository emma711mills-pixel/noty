package mg.s6.exams.controller;

import mg.s6.exams.entity.Candidat;
import mg.s6.exams.service.CandidatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidats")
public class CandidatController {

    private final CandidatService candidatService;

    public CandidatController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }

    // GET /api/candidats
    @GetMapping
    public List<Candidat> getAll() {
        return candidatService.findAll();
    }

    // GET /api/candidats/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getById(@PathVariable Long id) {
        return candidatService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/candidats
    @PostMapping
    public Candidat create(@RequestBody Candidat candidat) {
        return candidatService.save(candidat);
    }

    // PUT /api/candidats/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Candidat> update(@PathVariable Long id, @RequestBody Candidat candidat) {
        if (candidatService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(candidatService.update(id, candidat));
    }

    // DELETE /api/candidats/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (candidatService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        candidatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

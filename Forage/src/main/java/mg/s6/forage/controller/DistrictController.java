package mg.s6.forage.controller;

import mg.s6.forage.entity.District;
import mg.s6.forage.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping
    public List<District> getAllDistricts() {
        return districtService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable Long id) {
        return districtService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public District createDistrict(@RequestBody District district) {
        return districtService.save(district);
    }

    @PutMapping("/{id}")
    public ResponseEntity<District> updateDistrict(@PathVariable Long id, @RequestBody District districtDetails) {
        District updatedDistrict = districtService.update(id, districtDetails);
        if (updatedDistrict != null) {
            return ResponseEntity.ok(updatedDistrict);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Long id) {
        districtService.delete(id);
        return ResponseEntity.ok().build();
    }
}
package mg.s6.forage.service;

import mg.s6.forage.entity.Region;
import mg.s6.forage.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Optional<Region> findById(Long id) {
        return regionRepository.findById(id);
    }

    public Region save(Region region) {
        return regionRepository.save(region);
    }

    public Region update(Long id, Region regionDetails) {
        Optional<Region> regionOpt = regionRepository.findById(id);
        if (regionOpt.isPresent()) {
            Region region = regionOpt.get();
            region.setNom(regionDetails.getNom());
            return regionRepository.save(region);
        }
        return null;
    }

    public void delete(Long id) {
        regionRepository.deleteById(id);
    }
}
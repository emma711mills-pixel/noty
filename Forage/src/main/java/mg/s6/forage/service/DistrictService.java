package mg.s6.forage.service;

import mg.s6.forage.entity.District;
import mg.s6.forage.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    public List<District> findAll() {
        return districtRepository.findAll();
    }

    public Optional<District> findById(Long id) {
        return districtRepository.findById(id);
    }

    public District save(District district) {
        return districtRepository.save(district);
    }

    public District update(Long id, District districtDetails) {
        Optional<District> districtOpt = districtRepository.findById(id);
        if (districtOpt.isPresent()) {
            District district = districtOpt.get();
            district.setNom(districtDetails.getNom());
            district.setRegion(districtDetails.getRegion());
            return districtRepository.save(district);
        }
        return null;
    }

    public void delete(Long id) {
        districtRepository.deleteById(id);
    }
}
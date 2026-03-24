package mg.s6.forage.service;

import mg.s6.forage.entity.Commune;
import mg.s6.forage.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommuneService {

    @Autowired
    private CommuneRepository communeRepository;

    public List<Commune> findAll() {
        return communeRepository.findAll();
    }

    public Optional<Commune> findById(Long id) {
        return communeRepository.findById(id);
    }

    public Commune save(Commune commune) {
        return communeRepository.save(commune);
    }

    public Commune update(Long id, Commune communeDetails) {
        Optional<Commune> communeOpt = communeRepository.findById(id);
        if (communeOpt.isPresent()) {
            Commune commune = communeOpt.get();
            commune.setNom(communeDetails.getNom());
            commune.setDistrict(communeDetails.getDistrict());
            return communeRepository.save(commune);
        }
        return null;
    }

    public void delete(Long id) {
        communeRepository.deleteById(id);
    }
}
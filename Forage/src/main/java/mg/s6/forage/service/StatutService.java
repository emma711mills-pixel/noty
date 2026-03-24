package mg.s6.forage.service;

import mg.s6.forage.entity.Statut;
import mg.s6.forage.repository.StatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutService {

    @Autowired
    private StatutRepository statutRepository;

    public List<Statut> findAll() {
        return statutRepository.findAll();
    }

    public Optional<Statut> findById(Long id) {
        return statutRepository.findById(id);
    }

    public Statut save(Statut statut) {
        return statutRepository.save(statut);
    }

    public Statut update(Long id, Statut statutDetails) {
        Optional<Statut> statutOpt = statutRepository.findById(id);
        if (statutOpt.isPresent()) {
            Statut statut = statutOpt.get();
            statut.setLibelle(statutDetails.getLibelle());
            return statutRepository.save(statut);
        }
        return null;
    }

    public void delete(Long id) {
        statutRepository.deleteById(id);
    }
}
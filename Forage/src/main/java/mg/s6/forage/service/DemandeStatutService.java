package mg.s6.forage.service;

import mg.s6.forage.entity.DemandeStatut;
import mg.s6.forage.repository.DemandeStatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeStatutService {

    @Autowired
    private DemandeStatutRepository demandeStatutRepository;

    public List<DemandeStatut> findAll() {
        return demandeStatutRepository.findAll();
    }

    public DemandeStatut save(DemandeStatut demandeStatut) {
        return demandeStatutRepository.save(demandeStatut);
    }

    public DemandeStatut findLatestByDemandeId(Long demandeId) {
        return demandeStatutRepository.findLatestByDemandeId(demandeId);
    }

    public void delete(Long id) {
        demandeStatutRepository.deleteById(id);
    }
}
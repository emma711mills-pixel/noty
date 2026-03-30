package mg.s6.forage.service;

import mg.s6.forage.entity.Demande;
import mg.s6.forage.repository.DemandeRepository;
import mg.s6.forage.repository.DemandeSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    public List<Demande> findAll() {
        return demandeRepository.findAll();
    }

    public List<DemandeSummary> findAllWithLatestStatus() {
        return demandeRepository.findAllWithLatestStatus();
    }

    public Optional<Demande> findById(Long id) {
        return demandeRepository.findById(id);
    }

    public Demande save(Demande demande) {
        if (demande.getDateDemande() == null) {
            demande.setDateDemande(LocalDateTime.now());
        }
        return demandeRepository.save(demande);
    }

    public Optional<Demande> update(Long id, Demande demandeDetails) {
        return demandeRepository.findById(id).map(demande -> {
            demande.setClient(demandeDetails.getClient());
            demande.setCommune(demandeDetails.getCommune());
            return demandeRepository.save(demande);
        });
    }

    public void delete(Long id) {
        demandeRepository.deleteById(id);
    }
}
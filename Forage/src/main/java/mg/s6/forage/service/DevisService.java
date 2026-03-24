package mg.s6.forage.service;

import mg.s6.forage.entity.Devis;
import mg.s6.forage.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    public List<Devis> findAll() {
        return devisRepository.findAll();
    }

    public Optional<Devis> findById(Long id) {
        return devisRepository.findById(id);
    }

    public Devis save(Devis devis) {
        if (devis.getDateDevis() == null) {
            devis.setDateDevis(LocalDateTime.now());
        }
        if (devis.getStatutPaiement() == null) {
            devis.setStatutPaiement("NON_PAYE");
        }
        return devisRepository.save(devis);
    }

    public Optional<Devis> update(Long id, Devis devisDetails) {
        return devisRepository.findById(id).map(devis -> {
            devis.setMontantTotal(devisDetails.getMontantTotal());
            devis.setStatutPaiement(devisDetails.getStatutPaiement());
            devis.setDemande(devisDetails.getDemande());
            devis.setTypeDevis(devisDetails.getTypeDevis());
            devis.setStatut(devisDetails.getStatut());
            return devisRepository.save(devis);
        });
    }

    public void delete(Long id) {
        devisRepository.deleteById(id);
    }
}
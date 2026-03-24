package mg.s6.forage.service;

import mg.s6.forage.entity.Paiement;
import mg.s6.forage.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    public Optional<Paiement> findById(Long id) {
        return paiementRepository.findById(id);
    }

    public Paiement save(Paiement paiement) {
        if (paiement.getDatePaiement() == null) {
            paiement.setDatePaiement(LocalDateTime.now());
        }
        return paiementRepository.save(paiement);
    }

    public Paiement update(Long id, Paiement paiementDetails) {
        Optional<Paiement> paiementOpt = paiementRepository.findById(id);
        if (paiementOpt.isPresent()) {
            Paiement paiement = paiementOpt.get();
            paiement.setMontant(paiementDetails.getMontant());
            paiement.setModePaiement(paiementDetails.getModePaiement());
            paiement.setReference(paiementDetails.getReference());
            paiement.setDevis(paiementDetails.getDevis());
            return paiementRepository.save(paiement);
        }
        return null;
    }

    public void delete(Long id) {
        paiementRepository.deleteById(id);
    }
}
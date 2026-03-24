package mg.s6.forage.service;

import mg.s6.forage.entity.DetailDevis;
import mg.s6.forage.repository.DetailDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailDevisService {

    @Autowired
    private DetailDevisRepository detailDevisRepository;

    public List<DetailDevis> findAll() {
        return detailDevisRepository.findAll();
    }

    public Optional<DetailDevis> findById(Long id) {
        return detailDevisRepository.findById(id);
    }

    public DetailDevis save(DetailDevis detailDevis) {
        return detailDevisRepository.save(detailDevis);
    }

    public DetailDevis update(Long id, DetailDevis detailDevisDetails) {
        Optional<DetailDevis> detailDevisOpt = detailDevisRepository.findById(id);
        if (detailDevisOpt.isPresent()) {
            DetailDevis detailDevis = detailDevisOpt.get();
            detailDevis.setLibelle(detailDevisDetails.getLibelle());
            detailDevis.setMontant(detailDevisDetails.getMontant());
            detailDevis.setDevis(detailDevisDetails.getDevis());
            return detailDevisRepository.save(detailDevis);
        }
        return null;
    }

    public void delete(Long id) {
        detailDevisRepository.deleteById(id);
    }
}
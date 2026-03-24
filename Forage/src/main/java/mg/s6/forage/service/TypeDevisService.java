package mg.s6.forage.service;

import mg.s6.forage.entity.TypeDevis;
import mg.s6.forage.repository.TypeDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeDevisService {

    @Autowired
    private TypeDevisRepository typeDevisRepository;

    public List<TypeDevis> findAll() {
        return typeDevisRepository.findAll();
    }

    public Optional<TypeDevis> findById(Long id) {
        return typeDevisRepository.findById(id);
    }

    public TypeDevis save(TypeDevis typeDevis) {
        return typeDevisRepository.save(typeDevis);
    }

    public TypeDevis update(Long id, TypeDevis typeDevisDetails) {
        Optional<TypeDevis> typeDevisOpt = typeDevisRepository.findById(id);
        if (typeDevisOpt.isPresent()) {
            TypeDevis typeDevis = typeDevisOpt.get();
            typeDevis.setLibelle(typeDevisDetails.getLibelle());
            return typeDevisRepository.save(typeDevis);
        }
        return null;
    }

    public void delete(Long id) {
        typeDevisRepository.deleteById(id);
    }
}
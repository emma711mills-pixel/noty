package mg.s6.exams.service;

import mg.s6.exams.entity.Correcteur;
import mg.s6.exams.repository.CorrecteurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorrecteurService {

    private final CorrecteurRepository correcteurRepository;

    public CorrecteurService(CorrecteurRepository correcteurRepository) {
        this.correcteurRepository = correcteurRepository;
    }

    public List<Correcteur> findAll() {
        return correcteurRepository.findAll();
    }

    public Optional<Correcteur> findById(Long id) {
        return correcteurRepository.findById(id);
    }

    public Correcteur save(Correcteur correcteur) {
        return correcteurRepository.save(correcteur);
    }

    public Correcteur update(Long id, Correcteur correcteur) {
        correcteur.setIdCorrecteur(id);
        return correcteurRepository.save(correcteur);
    }

    public void deleteById(Long id) {
        correcteurRepository.deleteById(id);
    }
}

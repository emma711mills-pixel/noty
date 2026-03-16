package mg.s6.exams.service;

import mg.s6.exams.entity.Candidat;
import mg.s6.exams.repository.CandidatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatService {

    private final CandidatRepository candidatRepository;

    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

    public List<Candidat> findAll() {
        return candidatRepository.findAll();
    }

    public Optional<Candidat> findById(Long id) {
        return candidatRepository.findById(id);
    }

    public Candidat save(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public Candidat update(Long id, Candidat candidat) {
        candidat.setIdCandidat(id);
        return candidatRepository.save(candidat);
    }

    public void deleteById(Long id) {
        candidatRepository.deleteById(id);
    }
}

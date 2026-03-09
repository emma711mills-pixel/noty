package mg.s6.exams.repository;

import mg.s6.exams.entity.Matiere;
import mg.s6.exams.entity.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {

    List<Parametre> findByMatiere(Matiere matiere);
}

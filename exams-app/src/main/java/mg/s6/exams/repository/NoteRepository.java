package mg.s6.exams.repository;

import mg.s6.exams.entity.Candidat;
import mg.s6.exams.entity.Matiere;
import mg.s6.exams.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByCandidatAndMatiere(Candidat candidat, Matiere matiere);
}

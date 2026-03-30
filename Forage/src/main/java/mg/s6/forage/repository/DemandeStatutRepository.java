package mg.s6.forage.repository;

import mg.s6.forage.entity.DemandeStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeStatutRepository extends JpaRepository<DemandeStatut, Long> {

    List<DemandeStatut> findByDemandeIdOrderByDateDesc(Long demandeId);

    @Query("SELECT ds FROM DemandeStatut ds WHERE ds.demande.id = :demandeId ORDER BY ds.date DESC LIMIT 1")
    DemandeStatut findLatestByDemandeId(@Param("demandeId") Long demandeId);
}

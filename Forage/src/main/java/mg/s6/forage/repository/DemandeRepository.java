package mg.s6.forage.repository;

import mg.s6.forage.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

    @Query("SELECT d.id as id, " +
           "d.dateDemande as dateDemande, " +
           "c.nom as clientNom, " +
           "co.nom as communeNom, " +
           "s.libelle as statutLibelle " +
           "FROM Demande d " +
           "LEFT JOIN d.client c " +
           "LEFT JOIN d.commune co " +
           "LEFT JOIN DemandeStatut ds ON d.id = ds.demande.id " +
           "LEFT JOIN ds.statut s " +
           "WHERE ds.date = (SELECT MAX(ds2.date) FROM DemandeStatut ds2 WHERE ds2.demande.id = d.id) " +
           "OR ds.id IS NULL")
    List<DemandeSummary> findAllWithLatestStatus();
}
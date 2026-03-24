package mg.s6.forage.repository;

import mg.s6.forage.entity.DetailDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailDevisRepository extends JpaRepository<DetailDevis, Long> {
}
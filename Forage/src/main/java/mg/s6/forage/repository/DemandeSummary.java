package mg.s6.forage.repository;

import java.time.LocalDateTime;

public interface DemandeSummary {
    Long getId();
    LocalDateTime getDateDemande();
    String getClientNom();
    String getCommuneNom();
    String getStatutLibelle();
}
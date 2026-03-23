package mg.s6.exams.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "operateur")
public class Operateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idoperateur")
    private Long idOperateur;

    @Column(name = "operateur", nullable = false, length = 2)
    private String operateur;
}

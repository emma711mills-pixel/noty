package mg.s6.exams.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "correcteur")
public class Correcteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcorrecteur")
    private Long idCorrecteur;

    @Column(name = "nomcorrecteur", nullable = false)
    private String nomCorrecteur;
}

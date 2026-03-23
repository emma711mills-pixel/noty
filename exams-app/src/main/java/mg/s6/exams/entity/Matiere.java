package mg.s6.exams.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "matiere")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmatiere")
    private Long idMatiere;

    @Column(name = "nommatiere", nullable = false)
    private String nomMatiere;
}

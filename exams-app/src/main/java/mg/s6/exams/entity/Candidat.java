package mg.s6.exams.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "candidat")
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcandidat")
    private Long idCandidat;

    @Column(name = "nomcandidat", nullable = false)
    private String nomCandidat;
}

package mg.s6.exams.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "parametre")
public class Parametre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idparametre")
    private Long idParametre;

    @ManyToOne
    @JoinColumn(name = "id_matiere", nullable = false)
    private Matiere matiere;

    @Column(name = "difference", nullable = false)
    private BigDecimal difference;

    @ManyToOne
    @JoinColumn(name = "idoperateur", nullable = false)
    private Operateur operateur;

    @ManyToOne
    @JoinColumn(name = "idresolution", nullable = false)
    private Resolution resolution;
}

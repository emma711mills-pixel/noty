package mg.s6.exams.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "resolution")
public class Resolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idresolution")
    private Long idResolution;

    @Column(name = "nom", nullable = false)
    private String nom;
}

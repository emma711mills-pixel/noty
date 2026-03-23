package mg.s6.exams.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnote")
    private Long idNote;

    @ManyToOne
    @JoinColumn(name = "idcandidat", nullable = false)
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "idcorrecteur", nullable = false)
    private Correcteur correcteur;

    @ManyToOne
    @JoinColumn(name = "idmatiere", nullable = false)
    private Matiere matiere;

    @Column(name = "valeurnote", nullable = false)
    private BigDecimal valeurNote;
}

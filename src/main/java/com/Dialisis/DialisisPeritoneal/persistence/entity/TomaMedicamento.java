package com.Dialisis.DialisisPeritoneal.persistence.entity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tomamedicamento")
public class TomaMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTomaMedicamento;
    @ManyToOne
    @JoinColumn(name = "programarMedicamento")
    private ProgramarMedicamento programarMedicamento;
    private LocalDateTime hora;
    private boolean tomado;
}

package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "tomaMedicamento")
public class TomaMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTomaMedicamento;
    @ManyToOne
    @JoinColumn(name = "programarMedicamento")
    ProgramarMedicamento programarMedicamento;
    private LocalDateTime hora;
    private boolean tomado;
}

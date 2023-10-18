package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;
    @ManyToOne
    @JoinColumn(name = "medico")
    private Medico medico;
    @ManyToOne
    @JoinColumn(name = "paciente")
    private Paciente paciente;
    private LocalDateTime fecha;
    private LocalDateTime fechaFin;
    private LocalDateTime hora;
    private String orificioSalida;
    public Cita(){
        this.idCita=0;
    }

    public Cita(int idCita) {
        this.idCita = idCita;
    }
}

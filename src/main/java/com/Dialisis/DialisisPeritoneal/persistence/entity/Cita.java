package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    /*@ManyToOne
    @JoinColumn(name = "especialidad_medico")
    Especialidad especialidad_medico;*/
    @ManyToOne
    @JoinColumn(name = "paciente")
    private Paciente paciente;
    private String direccion;
    private LocalDateTime fecha;
    private LocalDateTime hora;
    private String orificioSalida;
    private boolean nocheSeca;
    public Cita(){
        this.idCita=0;
    }

    public Cita(int idCita) {
        this.idCita = idCita;
    }
}

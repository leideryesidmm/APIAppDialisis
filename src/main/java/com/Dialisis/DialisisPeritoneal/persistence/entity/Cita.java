package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cita;
    String nombre_medico;
    @ManyToOne
    @JoinColumn(name = "especialidad_medico")
    Especialidad especialidad_medico;
    @ManyToOne
    @JoinColumn(name = "paciente")
    Paciente paciente;
    private String lugar;
    private String direccion;
    private LocalDateTime fecha;

    public Cita(){
        this.id_cita=0;
    }

    public Cita(int id_cita) {
        this.id_cita = id_cita;
    }
}

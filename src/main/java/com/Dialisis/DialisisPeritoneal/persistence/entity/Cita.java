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
    private int idCita;
    @ManyToOne
    @JoinColumn(name = "medico")
    Medico cedulaMedico;

    @ManyToOne
    @JoinColumn(name = "paciente")
    Paciente cedulaPaciente;
    @ManyToOne
    @JoinColumn(name = "prescripcion")
    Prescripcion idPrescripcion;

    private String clinica;
    private String direccion;
    private LocalDateTime fecha;
    private LocalDateTime hora;

    public Cita(){
        this.idCita=0;
    }

    public Cita(int idCita) {
        this.idCita = idCita;
    }
}

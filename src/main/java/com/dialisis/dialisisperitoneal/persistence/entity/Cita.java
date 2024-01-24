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
    private boolean finalizado;
    public Cita(){
        this.idCita=0;
    }

    public Cita(int idCita) {
        this.idCita = idCita;
    }

    public Cita(int idCita, Medico medico, Paciente paciente, LocalDateTime fecha, LocalDateTime fechaFin, LocalDateTime hora, String orificioSalida, boolean finalizado) {
        this.idCita = idCita;
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.fechaFin = fechaFin;
        this.hora = hora;
        this.orificioSalida = orificioSalida;
        this.finalizado = finalizado;
    }

    public Cita(Cita c) {
        this.idCita = c.getIdCita();
        this.medico = c.getMedico();
        this.paciente = c.getPaciente();
        this.fecha = c.getFecha();
        this.fechaFin = c.getFechaFin();
        this.hora = c.getHora();
        this.orificioSalida = c.getOrificioSalida();
        this.finalizado = c.isFinalizado();
    }
}


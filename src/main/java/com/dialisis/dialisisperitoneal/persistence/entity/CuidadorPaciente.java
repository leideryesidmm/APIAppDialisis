package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "cuidadorPaciente")
public class CuidadorPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCuidadorPaciente;
    @ManyToOne
    @JoinColumn(name = "paciente")
    Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "cuidador")
    Cuidador cuidador;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activo;

    public CuidadorPaciente(int idCuidadorPaciente, Paciente paciente, Cuidador cuidador, LocalDate fechaInicio, LocalDate fechaFin, boolean activo) {
        this.idCuidadorPaciente = idCuidadorPaciente;
        this.paciente = paciente;
        this.cuidador = cuidador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
    }
    public CuidadorPaciente(CuidadorPaciente c) {
        this.idCuidadorPaciente = c.getIdCuidadorPaciente();
        this.paciente = c.getPaciente();
        this.cuidador = c.getCuidador();
        this.fechaInicio = c.getFechaInicio();
        this.fechaFin = c.getFechaFin();
        this.activo = c.isActivo();
    }
    public CuidadorPaciente(){
    }
}

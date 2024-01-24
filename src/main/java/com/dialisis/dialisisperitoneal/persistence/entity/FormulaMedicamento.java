package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "formulaMedicamento")
public class FormulaMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFormulaMedicamento;
    @ManyToOne
    @JoinColumn(name = "paciente")
    private Paciente paciente;
    private String tomas;
    private String nombre;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name="viaAdministracion")
    private ViaAdministracion viaAdministracion;
    private String concentracion;

    private LocalDateTime fechaIni;
    private LocalDateTime fechaFin;
    private boolean recetado;

    public FormulaMedicamento(){

        this.idFormulaMedicamento=0;
    }

    public FormulaMedicamento(int id ){

        this.idFormulaMedicamento=id;
    }

    public FormulaMedicamento(int idFormulaMedicamento, Paciente paciente, String tomas, String nombre, String descripcion, ViaAdministracion viaAdministracion, String concentracion, LocalDateTime fechaIni, LocalDateTime fechaFin, boolean recetado) {
        this.idFormulaMedicamento = idFormulaMedicamento;
        this.paciente = paciente;
        this.tomas = tomas;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.viaAdministracion = viaAdministracion;
        this.concentracion = concentracion;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.recetado = recetado;
    }

    public FormulaMedicamento(FormulaMedicamento f) {
        this.idFormulaMedicamento = f.idFormulaMedicamento;
        this.paciente = f.getPaciente();
        this.tomas = f.getTomas();
        this.nombre = f.getNombre();
        this.descripcion = f.getDescripcion();
        this.viaAdministracion = f.viaAdministracion;
        this.concentracion = f.getConcentracion();
        this.fechaIni = f.getFechaIni();
        this.fechaFin = f.getFechaFin();
        this.recetado = f.isRecetado();
    }
}

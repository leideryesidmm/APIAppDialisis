package com.Dialisis.DialisisPeritoneal.persistence.entity;

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
    private int intervaloTiempo;
    private int tomas;
    private int dosis;
    private String nombre;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name="viaAdministracion")
    private ViaAdministracion viaAdministracion;
    private double concentracion;

    private LocalDateTime fechaIni;
    private LocalDateTime fechaFin;

    public FormulaMedicamento(){

        this.idFormulaMedicamento=0;
    }

    public FormulaMedicamento(int id ){

        this.idFormulaMedicamento=id;
    }
}

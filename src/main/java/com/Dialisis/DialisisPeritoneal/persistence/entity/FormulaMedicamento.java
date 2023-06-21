package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "medicamento")
    private Medicamento medicamento;
    private int intervaloTiempo;
    private int tomas;
    private int dosis;

    public FormulaMedicamento(){

        this.idFormulaMedicamento=0;
    }

    public FormulaMedicamento(int id ){

        this.idFormulaMedicamento=id;
    }
}

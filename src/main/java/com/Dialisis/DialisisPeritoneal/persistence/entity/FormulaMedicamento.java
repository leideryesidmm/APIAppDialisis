package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "formula_medicamento")
public class FormulaMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_formula_medicamento;
    @ManyToOne
    @JoinColumn(name = "cita")
    Cita cita;
    @ManyToOne
    @JoinColumn(name = "medicamento")
    Medicamento medicamento;
    private int intervalo_tiempo;
    private int tomas;
    private int dosis;

    public FormulaMedicamento(){
        this.id_formula_medicamento=0;
    }

    public FormulaMedicamento(int id ){
        this.id_formula_medicamento=id;
    }
}

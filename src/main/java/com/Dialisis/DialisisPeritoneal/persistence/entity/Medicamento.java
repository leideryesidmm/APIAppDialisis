package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medicamento")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_medicamento;
    private String nombre;
    private String descripcion;
    private int via_Administracion;
    private int concentracion;

    public Medicamento(){
        this.id_medicamento=0;
    }

    public Medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }
}

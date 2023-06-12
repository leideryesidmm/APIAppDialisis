package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medicamento")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMedicamento;
    private String nombre;
    private String descripcion;
    private int viaAdministracion;
    private int concentracion;

    public Medicamento(){
        this.idMedicamento=0;
    }

    public Medicamento(int idMedicamento) {

        this.idMedicamento = idMedicamento;
    }
}

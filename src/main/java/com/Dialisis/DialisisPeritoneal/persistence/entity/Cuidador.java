package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cuidador")
public class Cuidador {
    @Id
    long cedula;
    String nombre;
    long celular;
    String direccion;
    @ManyToOne
    @JoinColumn(name = "parentesco")
    Parentesco parentesco;

    public Cuidador(){
        this.cedula=0;
    }

    public Cuidador(long cedula) {
        this.cedula = cedula;
    }

    public Cuidador(long cedula, String nombre, long celular, String direccion, Parentesco parentesco) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.celular = celular;
        this.direccion = direccion;
        this.parentesco = parentesco;
    }
}

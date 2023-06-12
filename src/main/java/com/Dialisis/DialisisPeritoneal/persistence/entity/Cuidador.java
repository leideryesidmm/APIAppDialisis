package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cuidador")
public class Cuidador {
    @Id
    String cedulaCuidador;
    String nombre;
    String telefono;
    String direccion;
    @ManyToOne
    @JoinColumn(name = "parentesco")
    Parentesco parentesco;

    public Cuidador(){
        this.cedulaCuidador=null;
    }

    public Cuidador(String cedulaCuidador) {
        this.cedulaCuidador = cedulaCuidador;
    }

    public Cuidador(String cedulaCuidador, String nombre, String telefono, String direccion, Parentesco parentesco) {
        this.cedulaCuidador = cedulaCuidador;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.parentesco = parentesco;
    }
}

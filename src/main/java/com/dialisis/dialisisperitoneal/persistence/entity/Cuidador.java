package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cuidador")
public class Cuidador {
    @Id
    private String cedulaCuidador;
    private String nombre;
    private String telefono;
    private String direccion;
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

    public Cuidador(Cuidador c){
        this.cedulaCuidador = c.getCedulaCuidador();
        this.nombre = c.getNombre();
        this.telefono = c.getTelefono();
        this.direccion = c.getDireccion();
        this.parentesco = c.getParentesco();
    }
}

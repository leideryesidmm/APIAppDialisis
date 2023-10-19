package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspecialidad;
    private String descripcion;

    public Especialidad(){
        this.idEspecialidad=0;
    }

    public Especialidad(int idEspecialidad) {

        this.idEspecialidad = idEspecialidad;
    }
}

package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medico")
@PrimaryKeyJoinColumn(referencedColumnName = "cedula")
public class Medico extends Usuario {
    private String cedula;
    @ManyToOne
    @JoinColumn(name = "especialidad")
    private Especialidad especialidad;
    private String profesion;

    public Medico(){
        this.cedula=null;
    }
    public Medico(String cedula) {
        this.cedula = cedula;
    }
}
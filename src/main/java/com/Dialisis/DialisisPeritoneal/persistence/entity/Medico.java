package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "medico")
@PrimaryKeyJoinColumn(referencedColumnName = "cedula")
public class Medico extends Usuario {
    private long cedula;
    private String especialidad;
    private int anios_de_experiencia;

    public Medico(){
        this.cedula=0;
    }
    public Medico(long cedula) {
        this.cedula = cedula;
    }
}
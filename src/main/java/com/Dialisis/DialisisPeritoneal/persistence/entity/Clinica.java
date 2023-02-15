package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clinica")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_clinica;
    private String nombre;
    private String direccion;

    public Clinica(){
        this.id_clinica=0;
    }
    public Clinica(int id_clinica) {
        this.id_clinica = id_clinica;
    }
}

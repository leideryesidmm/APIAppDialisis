package com.Dialisis.DialisisPeritoneal.persistence.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "jornada")
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_jornada;
    private String descripcion;

    public Jornada(){
        this.id_jornada=0;
    }

    public Jornada(int id_jornada) {
        this.id_jornada = id_jornada;
    }
}

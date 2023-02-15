package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "alergia")
public class Alergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_alergia;
    private String nombre;

    public Alergia(){
        this.id_alergia=0;
    }

    public Alergia(int id_alergia) {
        this.id_alergia = id_alergia;
    }

}

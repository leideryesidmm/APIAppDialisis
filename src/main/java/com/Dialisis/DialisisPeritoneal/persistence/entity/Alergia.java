package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "alergia")
public class Alergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlergia;
    private String nombre;

    public Alergia(){
        this.idAlergia=0;
    }

    public Alergia(int id_alergia) {
        this.idAlergia = idAlergia;
    }

}

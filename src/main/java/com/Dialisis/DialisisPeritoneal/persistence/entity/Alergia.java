package com.dialisis.dialisisperitoneal.persistence.entity;

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

    public Alergia(int idAlergia) {
        this.idAlergia = idAlergia;
    }

    public Alergia(int idAlergia, String nombre){
        this.idAlergia = idAlergia;
        this.nombre = nombre;
    }

}

package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "eps")
public class Eps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEps;
    private String nombre;

    public Eps(){
        this.idEps=0;
    }

    public Eps(int idEps) {
        this.idEps = idEps;
    }

    public Eps(int idEps, String nombre) {
        this.idEps = idEps;
        this.nombre = nombre;
    }
    public Eps(Eps e) {
        this.idEps = e.getIdEps();
        this.nombre = e.getNombre();
    }
}

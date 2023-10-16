package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "parentesco")
public class Parentesco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParentesco;
    private String descripcion;

    public Parentesco(){
        this.idParentesco=0;
    }

    public Parentesco(int idParentesco) {
        this.idParentesco = idParentesco;
    }
}

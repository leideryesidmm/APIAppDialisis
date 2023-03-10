package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "parentesco")
public class Parentesco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_parentesco;
    private String descripcion;

    public Parentesco(){
        this.id_parentesco=0;
    }

    public Parentesco(int id_parentesco) {
        this.id_parentesco = id_parentesco;
    }
}

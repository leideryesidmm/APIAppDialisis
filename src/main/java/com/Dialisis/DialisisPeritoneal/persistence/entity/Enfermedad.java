package com.Dialisis.DialisisPeritoneal.persistence.entity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "enfermedad")
public class Enfermedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_enfermedad;
    private String nombre;

    public Enfermedad(){
        this.id_enfermedad=0;
    }

    public Enfermedad(int id_enfermedad) {
        this.id_enfermedad = id_enfermedad;
    }
}

package com.Dialisis.DialisisPeritoneal.persistence.entity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "enfermedad")
public class Enfermedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEnfermedad;
    private String descripcion;

    public Enfermedad(){
        this.idEnfermedad=0;
    }

    public Enfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }
}

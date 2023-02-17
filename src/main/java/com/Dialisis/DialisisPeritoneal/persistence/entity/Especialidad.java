package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_especialidad_medico;
    private String descripcion;

    public Especialidad(){
        this.id_especialidad_medico=0;
    }

    public Especialidad(int id_especialidad_medico) {
        this.id_especialidad_medico = id_especialidad_medico;
    }
}

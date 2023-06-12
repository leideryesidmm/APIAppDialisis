package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspecialidadMedico;
    private String descripcion;

    public Especialidad(){
        this.idEspecialidadMedico=0;
    }

    public Especialidad(int idEspecialidadMedico) {

        this.idEspecialidadMedico = idEspecialidadMedico;
    }
}

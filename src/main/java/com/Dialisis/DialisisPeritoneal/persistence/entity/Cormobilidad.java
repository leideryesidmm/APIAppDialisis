package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cormobilidad")
public class Cormobilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCormobilidad;
    @ManyToOne
    @JoinColumn(name = "paciente")
    Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "enfermedad")
    Enfermedad enfermedad;
    boolean activo;
}

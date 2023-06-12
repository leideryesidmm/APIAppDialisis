package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "prescrpcion")
public class Prescripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrescripcion;
    private String orificioSalida;

    private boolean nocheSeca;

    public Prescripcion() {
        this.idPrescripcion = 0;
    }

    public Prescripcion(int idPrescripcion) {
        this.idPrescripcion = idPrescripcion;

    }
}
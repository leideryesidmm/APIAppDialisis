package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "prescripcion")
public class Prescripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrescripcion;
    private String orificioSalida;
    private boolean nocheSeca;

    public Prescripcion() {
        this.idPrescripcion = 0;
    }

    public Prescripcion(int idPrescripcion, String orificioSalida, boolean nocheSeca) {
        this.idPrescripcion = idPrescripcion;
        this.orificioSalida = orificioSalida;
        this.nocheSeca = nocheSeca;
    }
}
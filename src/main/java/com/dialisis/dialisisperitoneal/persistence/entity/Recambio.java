package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name="recambio")
public class Recambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecambio;
    @ManyToOne
    @JoinColumn(name = "prescripcion_dia")
    private PrescripcionDia prescripcionDia;
    private String concentracion;
    private String intervaloTiempo;

    public Recambio() {
    }

    public Recambio(int idRecambio) {
        this.idRecambio = idRecambio;
    }

    public Recambio(int idRecambio, PrescripcionDia prescripcionDia, String concentracion, String intervaloTiempo) {
        this.idRecambio = idRecambio;
        this.prescripcionDia = prescripcionDia;
        this.concentracion = concentracion;
        this.intervaloTiempo = intervaloTiempo;
    }

    public Recambio(Recambio r) {
        this.idRecambio = r.getIdRecambio();
        this.prescripcionDia = r.getPrescripcionDia();
        this.concentracion = r.getConcentracion();
        this.intervaloTiempo = r.getIntervaloTiempo();
    }
}

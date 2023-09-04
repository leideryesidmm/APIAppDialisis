package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private double concentracion;
    private int intervaloTiempo;

    public Recambio() {
    }

    public Recambio(int idRecambio) {
        this.idRecambio = idRecambio;
    }
}

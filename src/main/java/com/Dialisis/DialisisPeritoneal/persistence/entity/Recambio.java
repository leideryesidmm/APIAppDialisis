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
    @JoinColumn(name = "prescripcionDia")
    private PrescripcionDia prescripcionDia;
    private double concentración;
    private int intervaloTiempo;

    public Recambio(int idRecambio) {
        this.idRecambio = idRecambio;
    }
}

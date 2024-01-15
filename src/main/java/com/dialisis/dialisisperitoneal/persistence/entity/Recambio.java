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
}

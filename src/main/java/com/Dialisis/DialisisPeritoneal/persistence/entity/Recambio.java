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
    @JoinColumn(name = "prescripcion")
    private Prescripcion prescripcion;
    private LocalDateTime fecha;
    private LocalDateTime hora;
    private double concentración;
    private double drenajeDialisis;
    private boolean realizada;
    private String estadoLiquido;
}

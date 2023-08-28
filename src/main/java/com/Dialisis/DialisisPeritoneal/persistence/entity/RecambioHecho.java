package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Table(name = "RecambioHecho")
@Entity
public class RecambioHecho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecambioHecho;
    @ManyToOne
    @JoinColumn(name = "recambio")
    private Recambio recambio;
    private LocalDateTime fecha;
    private LocalDateTime fecha_real;
    private LocalDateTime hora_ini;
    private LocalDateTime hora_fin;
    private String drenajeDialisis;
    private String orificioSalida;
    private String caracteristicaLiquido;
}

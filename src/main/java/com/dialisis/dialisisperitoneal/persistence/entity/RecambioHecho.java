package com.dialisis.dialisisperitoneal.persistence.entity;

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
    @Column(name = "fecha_real")
    private LocalDateTime fechaReal;
    @Column(name = "hora_ini")
    private LocalDateTime horaIni;
    @Column(name = "hora_fin")
    private LocalDateTime horaFin;
    private String drenajeDialisis;
    private String orificioSalida;
    private String caracteristicaLiquido;
}

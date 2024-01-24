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
    private String liquidoEntrante;
    private String orificioSalida;
    private String caracteristicaLiquido;

    public RecambioHecho() {
    }

    public RecambioHecho(int idRecambioHecho, Recambio recambio, LocalDateTime fecha, LocalDateTime fechaReal, LocalDateTime horaIni, LocalDateTime horaFin, String drenajeDialisis, String liquidoEntrante, String orificioSalida, String caracteristicaLiquido) {
        this.idRecambioHecho = idRecambioHecho;
        this.recambio = recambio;
        this.fecha = fecha;
        this.fechaReal = fechaReal;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
        this.drenajeDialisis = drenajeDialisis;
        this.liquidoEntrante = liquidoEntrante;
        this.orificioSalida = orificioSalida;
        this.caracteristicaLiquido = caracteristicaLiquido;
    }

    public RecambioHecho(RecambioHecho rh) {
        this.idRecambioHecho = rh.getIdRecambioHecho();
        this.recambio = rh.getRecambio();
        this.fecha = rh.getFecha();
        this.fechaReal = rh.getFechaReal();
        this.horaIni = rh.getHoraIni();
        this.horaFin = rh.getHoraFin();
        this.drenajeDialisis = rh.getDrenajeDialisis();
        this.liquidoEntrante = rh.getLiquidoEntrante();
        this.orificioSalida = rh.getOrificioSalida();
        this.caracteristicaLiquido = rh.getCaracteristicaLiquido();
    }

}

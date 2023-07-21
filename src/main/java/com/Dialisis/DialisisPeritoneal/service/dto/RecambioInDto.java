package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RecambioInDto {
    private int idRecambio;
    private int prescripcion;
    private LocalDateTime fecha;
    private LocalDateTime hora;
    private double concentración;
    private double drenajeDialisis;
    private boolean realizada;
    private String estadoLiquido;
}

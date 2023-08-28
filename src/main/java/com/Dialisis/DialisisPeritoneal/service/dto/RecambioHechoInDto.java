package com.Dialisis.DialisisPeritoneal.service.dto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RecambioHechoInDto {
    private int recambio;
    private LocalDateTime fecha;
    private LocalDateTime fecha_real;
    private LocalDateTime hora_ini;
    private LocalDateTime hora_fin;
    private String drenajeDialisis;
    private String orificioSalida;
    private String caracteristicaLiquido;
}

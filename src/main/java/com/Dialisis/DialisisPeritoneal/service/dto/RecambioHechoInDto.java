package com.Dialisis.DialisisPeritoneal.service.dto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RecambioHechoInDto {
    private int recambio;
    private LocalDateTime fecha;
    private LocalDateTime hora;
    private String drenajeDialisis;
    private String orificioSalida;
    private String caracteristicaLiquido;
}

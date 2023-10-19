package com.dialisis.dialisisperitoneal.service.dto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RecambioHechoInDto {
    private int recambio;
    private LocalDateTime fecha;
    private LocalDateTime fechaReal;
    private LocalDateTime horaIni;
    private LocalDateTime horaFin;
    private String drenajeDialisis;
    private String orificioSalida;
    private String caracteristicaLiquido;
}

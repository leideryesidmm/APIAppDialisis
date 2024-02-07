package com.dialisis.dialisisperitoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CitaInDto {
    private String medico;
    private String paciente;
    private LocalDateTime fecha;
    private LocalDateTime fechaFin;
    private String orificioSalida;

}

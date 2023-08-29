package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class CitaInDto {
    private String medico;
    private String paciente;
    private LocalDateTime fecha;
    private LocalDateTime fechaFin;
    private LocalDateTime hora;
    private String orificioSalida;

}

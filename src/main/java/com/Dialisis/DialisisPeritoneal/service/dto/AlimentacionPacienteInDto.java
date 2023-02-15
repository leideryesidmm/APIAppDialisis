package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class AlimentacionPacienteInDto {
    private long paciente;
    private String alimentacion;
    private int jornada;
    private LocalDateTime fecha_hora;
    private int cantidad;
}

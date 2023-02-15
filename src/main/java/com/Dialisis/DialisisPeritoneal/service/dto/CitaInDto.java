package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class CitaInDto {
    private String nombre_medico;
    private int especialidad_medico;
    private long paciente;
    private String lugar;
    private String direccion;
    private LocalDateTime fecha;
}

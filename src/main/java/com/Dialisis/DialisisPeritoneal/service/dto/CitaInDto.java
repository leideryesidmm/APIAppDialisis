package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class CitaInDto {
    private String cedulaMedico;
    private String cedulaPaciente;
    private String clinica;
    private String direccion;
    private LocalDateTime fecha;
    private LocalDateTime hora;
    private int idPrescripcion;
}

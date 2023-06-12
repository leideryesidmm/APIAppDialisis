package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class CuidadorPacienteInDto {
    private String paciente;
    private String cuidador;
    private LocalDate fecha_ini;
    private LocalDate fecha_fin;
    private boolean activo;
}

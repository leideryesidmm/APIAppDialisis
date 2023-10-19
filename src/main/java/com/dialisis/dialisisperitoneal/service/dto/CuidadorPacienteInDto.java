package com.dialisis.dialisisperitoneal.service.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CuidadorPacienteInDto {
    private String paciente;
    private String cuidador;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private boolean activo;
}

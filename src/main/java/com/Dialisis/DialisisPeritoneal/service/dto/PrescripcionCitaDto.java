package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

@Data
public class PrescripcionCitaDto {
    private PrescripcionInDto prescripcion;
    private CitaInDto cita;

    // Getters y setters
}
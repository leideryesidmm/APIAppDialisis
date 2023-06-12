package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ProgramarMedicamentoInDto {
    private int idFormulaMedicamento;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

}

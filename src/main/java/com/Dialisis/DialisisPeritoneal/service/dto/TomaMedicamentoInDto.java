package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TomaMedicamentoInDto {
    private int programarMedicamento;
    private LocalDateTime hora;
    private boolean tomado;
}

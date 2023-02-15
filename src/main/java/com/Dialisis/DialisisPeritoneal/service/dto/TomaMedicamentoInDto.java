package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TomaMedicamentoInDto {
    private int programar_medicamento;
    private LocalDateTime hora;
    private boolean tomado;
}

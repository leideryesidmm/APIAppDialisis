package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ProgramarMedicamentoInDto {
    private int id_formula_medicamento;
    private LocalDateTime fecha_ini;
    private LocalDateTime fecha_fin;

}

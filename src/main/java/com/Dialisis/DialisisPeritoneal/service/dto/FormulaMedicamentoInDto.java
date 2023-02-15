package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

@Data
public class FormulaMedicamentoInDto {

    private int cita;
    private int medicamento;
    private int intervalo_tiempo;
    private int tomas;
    private int dosis;

}

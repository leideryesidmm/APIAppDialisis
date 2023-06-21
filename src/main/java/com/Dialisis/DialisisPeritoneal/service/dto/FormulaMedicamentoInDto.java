package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

@Data
public class FormulaMedicamentoInDto {

    private String paciente;
    private int medicamento;
    private int intervaloTiempo;
    private int tomas;
    private int dosis;

}

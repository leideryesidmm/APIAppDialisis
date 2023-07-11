package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FormulaMedicamentoInDto {

    private String paciente;
    private int medicamento;
    private int intervaloTiempo;
    private int tomas;
    private int dosis;
    private String nombre;
    private String descripcion;
    private int via_Administracion;
    private double concentracion;
    private LocalDateTime fechaIni;
    private LocalDateTime fechaFin;

}

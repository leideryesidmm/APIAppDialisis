package com.dialisis.dialisisperitoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FormulaMedicamentoInDto {

    private String paciente;
    private String tomas;
    private String nombre;
    private String descripcion;
    private int viaAdministracion;
    private String concentracion;
    private LocalDateTime fechaIni;
    private LocalDateTime fechaFin;
    private boolean recetado;

}

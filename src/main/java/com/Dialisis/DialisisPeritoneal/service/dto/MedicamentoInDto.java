package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

@Data
public class MedicamentoInDto {
    private String nombre;
    private String descripcion;
    private int viaAdministracion;
    private int concentracion;
}

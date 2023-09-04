package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RecambioInDto {
    private int prescripcionDia;
    private double concentracion;
    private int intervaloTiempo;
}



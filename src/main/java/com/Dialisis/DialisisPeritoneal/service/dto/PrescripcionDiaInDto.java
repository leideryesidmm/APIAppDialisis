package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

@Data
public class PrescripcionDiaInDto {
    private int prescripcion;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private boolean nocheSeca;

}

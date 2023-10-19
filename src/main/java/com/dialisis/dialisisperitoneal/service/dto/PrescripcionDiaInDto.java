package com.dialisis.dialisisperitoneal.service.dto;

import lombok.Data;

@Data
public class PrescripcionDiaInDto {
    private int cita;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private boolean nocheSeca;

}

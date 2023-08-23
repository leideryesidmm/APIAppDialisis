package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

@Data
public class VisitaEspecialistaInDto {

    private int cita;
    private boolean nefrologia;
    private boolean enfermeria;
    private boolean nutricion;
    private boolean psicologia;
    private boolean trabajoSocial;
    private boolean auxiliarAdmisiones;
    private boolean farmacia;
}

package com.dialisis.dialisisperitoneal.service.dto;

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
    private boolean entrenamiento;
    private boolean reentrenamiento;
    private boolean visitaDomiciliaria;
}

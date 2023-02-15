package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

@Data
public class MedicoInDto extends UsuarioInDto{
    private long cedula;
    private String especialidad;
    private int anios_de_experiencia;
}

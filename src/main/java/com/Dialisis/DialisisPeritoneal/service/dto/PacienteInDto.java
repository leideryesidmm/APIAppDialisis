package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PacienteInDto extends UsuarioInDto {
    private long cedula;
    private Date fechaNacimiento;
    private int edad;
    private String eps;
    private int altura;
    private double peso;
    private double peso_seco;
    private String direccion;
    private String ocupacion;
    private String tipo_sangre;
    private String rh;
}


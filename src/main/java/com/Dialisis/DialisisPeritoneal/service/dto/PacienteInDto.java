package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PacienteInDto extends UsuarioInDto {
    private String cedula;
    private Date fechaNacimiento;
    private int edad;
    private String eps;
    private int altura;
    private double peso;
    private double pesoSeco;
    private String direccion;
    private String ocupacion;
    private String tipoSangre;
    private String rh;
}


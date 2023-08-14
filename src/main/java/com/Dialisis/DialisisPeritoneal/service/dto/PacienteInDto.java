package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PacienteInDto extends UsuarioInDto {
    private String cedula;
    private LocalDateTime fechaNacimiento;
    private String eps;
    private int altura;
    private float peso;
    private float pesoSeco;
    private String direccion;
    private String ocupacion;
    private String tipoSangre;
    private char rh;
    private boolean diabetes;
    private boolean hipertension;
    private boolean activo;
}


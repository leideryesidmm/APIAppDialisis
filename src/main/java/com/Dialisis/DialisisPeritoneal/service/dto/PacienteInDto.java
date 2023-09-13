package com.Dialisis.DialisisPeritoneal.service.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PacienteInDto extends UsuarioInDto {
    private String cedula;
    private LocalDateTime fechaNacimiento;
    private int eps;
    private int altura;
    private float peso;
    private float pesoSeco;
    private String direccion;
    private String ocupacion;
    private String tipoSangre;
    private String tipo_documento;
    private char rh;
    private boolean diabetes;
    private boolean hipertension;
    private boolean cambio_contrasenia;
}


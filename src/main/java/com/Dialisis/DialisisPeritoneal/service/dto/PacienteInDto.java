package com.dialisis.dialisisperitoneal.service.dto;

import lombok.Data;
import java.time.LocalDateTime;
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
    private LocalDateTime fechaRegistro;
    private char rh;
    private boolean diabetes;
    private boolean hipertension;
    private boolean cambioContrasenia;
}


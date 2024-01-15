package com.dialisis.dialisisperitoneal.service.dto;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class PacienteInDto extends UsuarioInDto {
    private String cedula;
    private LocalDateTime fechaNacimiento;
    private int eps;
    private String altura;
    private String peso;
    private String pesoSeco;
    private String direccion;
    private String ocupacion;
    private String tipoSangre;
    private LocalDateTime fechaRegistro;
    private String rh;
    private boolean diabetes;
    private boolean hipertension;
    private boolean cambioContrasenia;
}


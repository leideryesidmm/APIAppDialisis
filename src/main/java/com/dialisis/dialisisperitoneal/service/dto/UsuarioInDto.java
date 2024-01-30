package com.dialisis.dialisisperitoneal.service.dto;


import lombok.Data;

import javax.persistence.Lob;

@Data
public class UsuarioInDto {
    private String cedula;
    private String nombre;
    private String contrasenia;
    private String celular;
    private String correo;
    private boolean activo;
    private String tipoDocumento;
}

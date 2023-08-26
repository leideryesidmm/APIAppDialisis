package com.Dialisis.DialisisPeritoneal.service.dto;


import lombok.Data;

import javax.persistence.Lob;

@Data
public class UsuarioInDto {
    private String cedula;
    private String nombre;
    private String contrasenia;
    private String celular;
    @Lob
    private byte[] foto;
}

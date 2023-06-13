package com.Dialisis.DialisisPeritoneal.service.dto;


import lombok.Data;

@Data
public class UsuarioInDto {
    private String cedula;
    private String nombre;
    private String correo;
    private String contrasenia;
    private String celular;
}

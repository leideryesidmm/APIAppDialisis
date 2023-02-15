package com.Dialisis.DialisisPeritoneal.service.dto;


import lombok.Data;

@Data
public class UsuarioInDto {
    private long cedula;
    private String nombre;
    private String correo;
    private String contrasenia;
    private long celular;
}

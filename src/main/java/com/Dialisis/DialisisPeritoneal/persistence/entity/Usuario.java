package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    private long cedula;
    private String nombre;
    private String correo;
    private String contrasenia;
    private long celular;

    public Usuario() {
    }
}


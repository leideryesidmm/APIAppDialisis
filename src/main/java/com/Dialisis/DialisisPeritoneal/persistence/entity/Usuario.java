package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    private String cedula;
    private String nombre;
    private String contrasenia;
    private String celular;

    public Usuario() {
    }
}


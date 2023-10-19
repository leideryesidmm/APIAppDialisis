package com.dialisis.dialisisperitoneal.persistence.entity;

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
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    private String correo;
    private boolean activo;
    private String tipoUsuario="user";
    @Column(name = "tipo_documento")
    private String tipoDocumento;

    public Usuario() {
        //Fue necesario añadir un constructor vacio para crear un objeto usuario sin datos en otra seccion
    }

}


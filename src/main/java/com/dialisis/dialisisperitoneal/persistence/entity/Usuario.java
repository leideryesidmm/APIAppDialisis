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

    private String correo;
    private boolean activo;
    private String tipoUsuario="user";
    @Column(name = "tipo_documento")
    private String tipoDocumento;

    public Usuario() {
    }

    public Usuario(String cedula, String nombre, String contrasenia, String celular, String correo, boolean activo, String tipoUsuario, String tipoDocumento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.celular = celular;
        this.correo = correo;
        this.activo = activo;
        this.tipoUsuario = tipoUsuario;
        this.tipoDocumento = tipoDocumento;
    }

    public Usuario(Usuario u) {
        this.cedula = u.getCedula();
        this.nombre = u.getNombre();
        this.contrasenia = u.getContrasenia();
        this.celular = u.getCelular();
        this.correo = u.getCorreo();
        this.activo = u.isActivo();
        this.tipoUsuario = u.getTipoUsuario();
        this.tipoDocumento = u.getTipoDocumento();
    }
}


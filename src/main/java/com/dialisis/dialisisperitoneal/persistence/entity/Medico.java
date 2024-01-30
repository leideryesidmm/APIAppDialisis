package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medico")
@PrimaryKeyJoinColumn(referencedColumnName = "cedula")
public class Medico extends Usuario {
    private String cedula;
    @ManyToOne
    @JoinColumn(name = "especialidad")
    private Especialidad especialidad;
    private String profesion;

    public Medico(){
    }
    public Medico(String cedula) {
        this.cedula = cedula;
    }

    public Medico(Medico m){
        this.cedula=m.getCedula();
        this.especialidad=m.getEspecialidad();
        this.profesion=m.getProfesion();
        this.setCelular(m.getCelular());
        this.setContrasenia(m.getContrasenia());
        this.setCorreo(m.getCorreo());
        this.setNombre(m.getNombre());
        this.setTipoDocumento(m.getTipoDocumento());
    }

    public Medico(String cedula, String nombre, String contrasenia, String celular,  String correo, boolean activo, String tipoUsuario, String tipoDocumento, String cedula1, Especialidad especialidad, String profesion) {
        super(cedula, nombre, contrasenia, celular,  correo, activo, tipoUsuario, tipoDocumento);
        this.cedula = cedula1;
        this.especialidad = especialidad;
        this.profesion = profesion;
    }
}
package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "paciente")
@PrimaryKeyJoinColumn(referencedColumnName = "cedula")
public class Paciente extends Usuario{
@Id
private String cedula;
    private LocalDateTime fechaNacimiento;
    @Column(name="fecha_registro")
    private LocalDateTime fechaRegistro;
    @ManyToOne
    @JoinColumn(name="eps")
    private Eps eps;
    private String altura;
    private String peso;
    private String pesoSeco;
    private String direccion;
    private String ocupacion;
    private String tipoSangre;
    private String rh;

    private boolean diabetes;
    private boolean hipertension;
    @Column(name="cambio_contrasenia")
    private boolean cambioContrasenia;


    public Paciente(){
        this.cedula=null;
    }
    public Paciente(String id){
        this.cedula=id;
    }

    public Paciente(String cedula,  boolean cambioContrasenia,LocalDateTime fechaRegistro, LocalDateTime fechaNacimiento, String altura, String peso, String pesoSeco, String direccion, String ocupacion, String tipoSangre, String rh, boolean diabetes, boolean hipertension, Eps eps) {
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.altura = altura;
        this.peso = peso;
        this.fechaRegistro=fechaRegistro;
        this.pesoSeco = pesoSeco;
        this.direccion = direccion;
        this.ocupacion = ocupacion;
        this.tipoSangre = tipoSangre;
        this.rh = rh;
        this.diabetes=diabetes;
        this.hipertension=hipertension;
        this.eps=eps;
        this.cambioContrasenia=cambioContrasenia;
    }
    public Paciente(Paciente p){
        this.cedula = p.getCedula();
        this.fechaNacimiento = p.getFechaNacimiento();
        this.altura = p.getAltura();
        this.peso = p.getPeso();
        this.fechaRegistro=p.getFechaRegistro();
        this.pesoSeco = p.getPesoSeco();
        this.direccion = p.getDireccion();
        this.ocupacion = p.getOcupacion();
        this.tipoSangre = p.getTipoSangre();
        this.rh = p.getRh();
        this.diabetes=p.isDiabetes();
        this.hipertension=p.isHipertension();
        this.eps=p.getEps();
        this.cambioContrasenia=p.isCambioContrasenia();
    }
}

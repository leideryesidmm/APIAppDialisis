package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "paciente")
@PrimaryKeyJoinColumn(referencedColumnName = "cedula")
public class Paciente extends Usuario{
@Id
    private String cedula;
    private Date fechaNacimiento;
    private int edad;
    private String eps;
    private int altura;
    private double peso;
    private double pesoSeco;
    private String direccion;
    private String ocupacion;
    private String tipoSangre;
    private String rh;

    public Paciente(){
        this.cedula=null;
    }
    public Paciente(String id){
        this.cedula=id;
    }

    public Paciente(String cedula, Date fechaNacimiento, int edad, String eps, int altura, int peso, int pesoSeco, String direccion, String ocupacion, String tipoSangre, String rh) {
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.eps = eps;
        this.altura = altura;
        this.peso = peso;
        this.pesoSeco = pesoSeco;
        this.direccion = direccion;
        this.ocupacion = ocupacion;
        this.tipoSangre = tipoSangre;
        this.rh = rh;
    }
}

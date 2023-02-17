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
    private long cedula;
    private Date fechaNacimiento;
    private int edad;
    private String eps;
    private int altura;
    private double peso;
    private double peso_seco;
    private String direccion;
    private String ocupacion;
    private String tipo_sangre;
    private String rh;

    public Paciente(){
        this.cedula=0;
    }
    public Paciente(long id){
        this.cedula=id;
    }

    public Paciente(long cedula, Date fechaNacimiento, int edad, String eps, int altura, int peso, int peso_seco, String direccion, String ocupacion, String tipo_sangre, String rh) {
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.eps = eps;
        this.altura = altura;
        this.peso = peso;
        this.peso_seco = peso_seco;
        this.direccion = direccion;
        this.ocupacion = ocupacion;
        this.tipo_sangre = tipo_sangre;
        this.rh = rh;
    }
}

package com.Dialisis.DialisisPeritoneal.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "paciente")
@PrimaryKeyJoinColumn(referencedColumnName = "cedula")
public class Paciente extends Usuario{
@Id
private String cedula;
    private LocalDateTime fechaNacimiento;
    private String eps;
    private int altura;
    private float peso;
    private float pesoSeco;
    private String direccion;
    private String ocupacion;
    private String tipoSangre;
    private char rh;
    private boolean diabetes;
    private boolean hipertension;

    public Paciente(){
        this.cedula=null;
    }
    public Paciente(String id){
        this.cedula=id;
    }

    public Paciente(String cedula, LocalDateTime fechaNacimiento, String eps, int altura, int peso, int pesoSeco, String direccion, String ocupacion, String tipoSangre, char rh, boolean diabetes, boolean hipertension) {
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.eps = eps;
        this.altura = altura;
        this.peso = peso;
        this.pesoSeco = pesoSeco;
        this.direccion = direccion;
        this.ocupacion = ocupacion;
        this.tipoSangre = tipoSangre;
        this.rh = rh;
        this.diabetes=diabetes;
        this.hipertension=hipertension;
    }
}

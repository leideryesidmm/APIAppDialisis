package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clinica")
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClinica;
    private String nombre;
    private String direccion;

    public Clinica(){
        this.idClinica=0;
    }
    public Clinica(int idClinica) {
        this.idClinica = idClinica;
    }
}

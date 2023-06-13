package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "medicoClinica")
public class MedicoClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMedicoClinica;
    @ManyToOne
    @JoinColumn(name = "medico")
    Medico medico;
    @ManyToOne
    @JoinColumn(name = "clinica")
    Clinica clinica;
    boolean activa;
}

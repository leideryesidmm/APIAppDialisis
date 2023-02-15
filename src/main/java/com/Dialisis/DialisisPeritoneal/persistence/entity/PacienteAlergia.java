package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "paciente_alergia")
public class PacienteAlergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_paciente_alergia;

    @ManyToOne
    @JoinColumn(name = "paciente")
    Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "alergia")
    Alergia alergia;
    boolean activa;

}

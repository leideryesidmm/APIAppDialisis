package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pacienteAlergia")
public class PacienteAlergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPacienteAlergia;

    @ManyToOne
    @JoinColumn(name = "paciente")
    Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "alergia")
    Alergia alergia;
    boolean activo;

}

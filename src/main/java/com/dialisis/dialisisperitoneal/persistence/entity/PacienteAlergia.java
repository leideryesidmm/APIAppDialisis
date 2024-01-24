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

    public PacienteAlergia() {
    }

    public PacienteAlergia(int idPacienteAlergia, Paciente paciente, Alergia alergia, boolean activo) {
        this.idPacienteAlergia = idPacienteAlergia;
        this.paciente = paciente;
        this.alergia = alergia;
        this.activo = activo;
    }

    public PacienteAlergia(PacienteAlergia pa) {
        this.idPacienteAlergia = pa.getIdPacienteAlergia();
        this.paciente = pa.getPaciente();
        this.alergia = pa.getAlergia();
        this.activo = pa.isActivo();
    }
}

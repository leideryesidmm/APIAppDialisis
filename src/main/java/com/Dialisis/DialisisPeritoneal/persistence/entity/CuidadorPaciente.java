package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "cuidadorPaciente")
public class CuidadorPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCuidadorPaciente;
    @ManyToOne
    @JoinColumn(name = "paciente")
    Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "cuidador")
    Cuidador cuidador;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activo;


}

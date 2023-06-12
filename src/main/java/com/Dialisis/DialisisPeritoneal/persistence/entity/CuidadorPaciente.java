package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

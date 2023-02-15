package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "cuidador_paciente")
public class CuidadorPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cuidador_paciente;
    @ManyToOne
    @JoinColumn(name = "paciente")
    Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "cuidador")
    Cuidador cuidador;

    private LocalDateTime fecha_ini;
    private LocalDateTime fecha_fin;
    private boolean activo;
}

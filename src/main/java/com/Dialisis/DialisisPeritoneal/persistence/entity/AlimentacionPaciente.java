package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "alimentacion_paciente")
public class AlimentacionPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_alimentacion_paciente;
    @ManyToOne
    @JoinColumn(name = "paciente")
    Paciente paciente;
    String alimentacion;
    @ManyToOne
    @JoinColumn(name = "jornada")
    Jornada jornada;
    private LocalDateTime fecha_hora;
    private int cantidad;

    public AlimentacionPaciente(){
        this.id_alimentacion_paciente=0;
    }
    public AlimentacionPaciente(int id_alimentacion_paciente) {
        this.id_alimentacion_paciente = id_alimentacion_paciente;
    }
}

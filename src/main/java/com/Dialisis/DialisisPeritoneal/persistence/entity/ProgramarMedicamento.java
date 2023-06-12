package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "programarMedicamento")
public class ProgramarMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProgramarMedicamento;
    @ManyToOne
    @JoinColumn(name = "idFormulaMedicamento")
    FormulaMedicamento formulaMedicamento;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public ProgramarMedicamento(){
        this.idProgramarMedicamento=0;
    }

    public ProgramarMedicamento(int id){
        this.idProgramarMedicamento=id;
    }
}

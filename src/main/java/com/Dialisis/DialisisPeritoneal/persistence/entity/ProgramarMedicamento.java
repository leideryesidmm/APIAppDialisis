package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "programar_medicamento")
public class ProgramarMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_programar_medicamento;
    @ManyToOne
    @JoinColumn(name = "id_formula_medicamento")
    FormulaMedicamento formulaMedicamento;
    private LocalDateTime fecha_ini;
    private LocalDateTime fecha_fin;

    public ProgramarMedicamento(){
        this.id_programar_medicamento=0;
    }

    public ProgramarMedicamento(int id){
        this.id_programar_medicamento=id;
    }
}

package com.Dialisis.DialisisPeritoneal.persistence.entity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="intervalosHoras")
public class IntervalosHoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIntervalosHoras;
    @ManyToOne
    @JoinColumn(name = "prescripcion")
    private Prescripcion prescripcion;
    private String descripcion;


}

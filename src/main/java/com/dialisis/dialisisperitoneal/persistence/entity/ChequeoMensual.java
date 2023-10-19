package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="chequeomensual")
public class ChequeoMensual {
    @Id
    private int idChequeoMensual;
    @ManyToOne
    @JoinColumn(name = "cita")
    private Cita cita;
    private double tensionArterial;
    private double colesterolTotal;
    private double hemoglobina;
    private double trigliceridos;
    private double glicemia;
    private double hdl;
    private double ldh;
    private double potasio;
    private double fosforo;
    private double nitrogenoUreico;
    private double peso;
    @Column(name="peso_seco")
    private double pesoSeco;
    private double ktv;
    private double glucosa;
    private double creatinina;

}

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
    private String tensionArterial;
    private String colesterolTotal;
    private String hemoglobina;
    private String trigliceridos;
    private String glicemia;
    private String hdl;
    private String ldh;
    private String potasio;
    private String fosforo;
    private String nitrogenoUreico;
    private String peso;
    @Column(name="peso_seco")
    private String pesoSeco;
    private String ktv;
    private String glucosa;
    private String creatinina;

}

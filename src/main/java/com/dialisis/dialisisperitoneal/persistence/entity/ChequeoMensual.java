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

    public ChequeoMensual(int idChequeoMensual, Cita cita, String tensionArterial, String colesterolTotal, String hemoglobina, String trigliceridos, String glicemia, String hdl, String ldh, String potasio, String fosforo, String nitrogenoUreico, String peso, String pesoSeco, String ktv, String glucosa, String creatinina) {
        this.idChequeoMensual = idChequeoMensual;
        this.cita = cita;
        this.tensionArterial = tensionArterial;
        this.colesterolTotal = colesterolTotal;
        this.hemoglobina = hemoglobina;
        this.trigliceridos = trigliceridos;
        this.glicemia = glicemia;
        this.hdl = hdl;
        this.ldh = ldh;
        this.potasio = potasio;
        this.fosforo = fosforo;
        this.nitrogenoUreico = nitrogenoUreico;
        this.peso = peso;
        this.pesoSeco = pesoSeco;
        this.ktv = ktv;
        this.glucosa = glucosa;
        this.creatinina = creatinina;
    }
    public ChequeoMensual(ChequeoMensual c) {
        this.idChequeoMensual = c.getIdChequeoMensual();
        this.cita = c.getCita();
        this.tensionArterial = c.getTensionArterial();
        this.colesterolTotal = c.getColesterolTotal();
        this.hemoglobina = c.getHemoglobina();
        this.trigliceridos = c.getTrigliceridos();
        this.glicemia = c.getGlicemia();
        this.hdl = c.getHdl();
        this.ldh = c.getLdh();
        this.potasio = c.getPotasio();
        this.fosforo = c.getFosforo();
        this.nitrogenoUreico = c.getNitrogenoUreico();
        this.peso = c.getPeso();
        this.pesoSeco = c.getPesoSeco();
        this.ktv = c.getKtv();
        this.glucosa = c.getGlucosa();
        this.creatinina = c.getCreatinina();
    }
}

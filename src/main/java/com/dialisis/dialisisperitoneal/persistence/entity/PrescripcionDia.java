package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name = "prescripcionDia")
public class PrescripcionDia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrescripcionDia;
    @ManyToOne
    @JoinColumn(name = "cita")
    private Cita cita;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private boolean nocheSeca;

    public PrescripcionDia() {
    }

    public PrescripcionDia(int idPrescripcionDia) {

        this.idPrescripcionDia = idPrescripcionDia;
    }

    public PrescripcionDia(PrescripcionDia p) {
        this.cita=p.getCita();
        this.lunes=p.isLunes();
        this.martes=p.isMartes();
        this.miercoles=p.isMiercoles();
        this.jueves=p.isJueves();
        this.viernes=p.isViernes();
        this.sabado=p.isSabado();
        this.domingo=p.isDomingo();
        this.nocheSeca=p.isNocheSeca();
    }
}

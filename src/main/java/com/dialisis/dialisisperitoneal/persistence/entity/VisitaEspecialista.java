package com.dialisis.dialisisperitoneal.persistence.entity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "visitaEspecialista")
public class VisitaEspecialista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVistaEspecialista;
    @ManyToOne
    @JoinColumn(name = "cita")
    private Cita cita;
    private boolean nefrologia;
    private boolean enfermeria;
    private boolean nutricion;
    private boolean psicologia;
    private boolean trabajoSocial;
    private boolean auxiliarAdmisiones;
    private boolean farmacia;
    private boolean entrenamiento;
    private boolean reentrenamiento;
    @Column(name = "visita_domiciliaria")
    private boolean visitaDomiciliaria;


    public VisitaEspecialista() {
    }

    public VisitaEspecialista(int idVistaEspecialista, Cita cita, boolean nefrologia, boolean enfermeria, boolean nutricion, boolean psicologia, boolean trabajoSocial, boolean auxiliarAdmisiones, boolean farmacia, boolean entrenamiento, boolean reentrenamiento, boolean visitaDomiciliaria) {
        this.idVistaEspecialista = idVistaEspecialista;
        this.cita = cita;
        this.nefrologia = nefrologia;
        this.enfermeria = enfermeria;
        this.nutricion = nutricion;
        this.psicologia = psicologia;
        this.trabajoSocial = trabajoSocial;
        this.auxiliarAdmisiones = auxiliarAdmisiones;
        this.farmacia = farmacia;
        this.entrenamiento = entrenamiento;
        this.reentrenamiento = reentrenamiento;
        this.visitaDomiciliaria = visitaDomiciliaria;
    }

    public VisitaEspecialista(VisitaEspecialista v) {
        this.idVistaEspecialista = v.getIdVistaEspecialista();
        this.cita = v.getCita();
        this.nefrologia = v.isNefrologia();
        this.enfermeria = v.isEnfermeria();
        this.nutricion = v.isNutricion();
        this.psicologia = v.isPsicologia();
        this.trabajoSocial = v.isTrabajoSocial();
        this.auxiliarAdmisiones = v.isAuxiliarAdmisiones();
        this.farmacia = v.isFarmacia();
        this.entrenamiento = v.isEntrenamiento();
        this.reentrenamiento = v.isReentrenamiento();
        this.visitaDomiciliaria = v.isVisitaDomiciliaria();
    }
}

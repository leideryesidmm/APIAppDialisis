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


}

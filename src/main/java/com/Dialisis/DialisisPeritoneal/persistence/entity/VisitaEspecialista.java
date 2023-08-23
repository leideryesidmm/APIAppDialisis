package com.Dialisis.DialisisPeritoneal.persistence.entity;
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

}

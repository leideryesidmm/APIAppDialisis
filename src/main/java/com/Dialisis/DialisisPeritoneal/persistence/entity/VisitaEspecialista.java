package com.Dialisis.DialisisPeritoneal.persistence.entity;
import com.Dialisis.DialisisPeritoneal.persistence.repository.VisitaEspecialistaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.FormulaMedicamentoInDto;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;

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
    private boolean visita_domiciliaria;


}

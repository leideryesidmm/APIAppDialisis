package com.Dialisis.DialisisPeritoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tipoRecambio")
public class TipoRecambio {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tipo;
    private String descripcion;

}

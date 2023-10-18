package com.dialisis.dialisisperitoneal.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "viaAdministracion")
public class ViaAdministracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idViaAdministracion;
    private String descripcion;

    public ViaAdministracion(int idViaAdministracion) {
        this.idViaAdministracion = idViaAdministracion;
    }

    public ViaAdministracion(int idViaAdministracion, String descripcion) {
        this.idViaAdministracion = idViaAdministracion;
        this.descripcion = descripcion;
    }

    public ViaAdministracion() {
    }
}

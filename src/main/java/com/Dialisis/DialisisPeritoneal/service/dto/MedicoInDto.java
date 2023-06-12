package com.Dialisis.DialisisPeritoneal.service.dto;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Especialidad;
import lombok.Data;

@Data
public class MedicoInDto extends UsuarioInDto{
    private String cedula;
    private Especialidad especialidad;
    private int aniosExperiencia;
}

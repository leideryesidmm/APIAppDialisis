package com.dialisis.dialisisperitoneal.service.dto;

import com.dialisis.dialisisperitoneal.persistence.entity.Especialidad;
import lombok.Data;

@Data
public class MedicoInDto extends UsuarioInDto{
    private String cedula;
    private Especialidad especialidad;
    private String profesion;
}

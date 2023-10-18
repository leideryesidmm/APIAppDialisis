package com.dialisis.dialisisperitoneal.service.dto.uniones;

import com.dialisis.dialisisperitoneal.service.dto.AlergiaInDto;
import com.dialisis.dialisisperitoneal.service.dto.PacienteInDto;
import lombok.Data;

@Data
public class    UnionPacienteAlergiaInDto {

    private PacienteInDto pacienteInDto;

    private AlergiaInDto alergiaInDto;
}

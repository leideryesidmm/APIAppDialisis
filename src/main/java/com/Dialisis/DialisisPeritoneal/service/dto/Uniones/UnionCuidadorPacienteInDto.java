package com.dialisis.dialisisperitoneal.service.dto.uniones;

import com.dialisis.dialisisperitoneal.service.dto.CuidadorInDto;
import com.dialisis.dialisisperitoneal.service.dto.PacienteInDto;
import lombok.Data;

@Data
public class UnionCuidadorPacienteInDto {

    private PacienteInDto pacienteInDto;

    private CuidadorInDto cuidadorInDto;
}

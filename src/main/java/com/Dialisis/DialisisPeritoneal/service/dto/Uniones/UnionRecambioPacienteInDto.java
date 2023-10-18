package com.dialisis.dialisisperitoneal.service.dto.uniones;

import com.dialisis.dialisisperitoneal.service.dto.PacienteInDto;
import com.dialisis.dialisisperitoneal.service.dto.RecambioInDto;
import lombok.Data;

@Data
public class UnionRecambioPacienteInDto {

    private RecambioInDto recambioInDto;

    private PacienteInDto pacienteInDto;
}

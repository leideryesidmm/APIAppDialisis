package com.Dialisis.DialisisPeritoneal.service.dto.Uniones;

import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.RecambioInDto;
import lombok.Data;

@Data
public class UnionRecambioPacienteInDto {

    private RecambioInDto recambioInDto;

    private PacienteInDto pacienteInDto;
}

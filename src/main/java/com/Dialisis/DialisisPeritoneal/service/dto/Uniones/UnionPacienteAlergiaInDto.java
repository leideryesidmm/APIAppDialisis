package com.Dialisis.DialisisPeritoneal.service.dto.Uniones;

import com.Dialisis.DialisisPeritoneal.service.dto.AlergiaInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import lombok.Data;

@Data
public class    UnionPacienteAlergiaInDto {

    private PacienteInDto pacienteInDto;

    private AlergiaInDto alergiaInDto;
}

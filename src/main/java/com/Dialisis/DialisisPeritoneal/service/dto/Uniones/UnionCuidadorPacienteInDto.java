package com.Dialisis.DialisisPeritoneal.service.dto.Uniones;

import com.Dialisis.DialisisPeritoneal.service.dto.CuidadorInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import lombok.Data;

@Data
public class UnionCuidadorPacienteInDto {

    private PacienteInDto pacienteInDto;

    private CuidadorInDto cuidadorInDto;
}

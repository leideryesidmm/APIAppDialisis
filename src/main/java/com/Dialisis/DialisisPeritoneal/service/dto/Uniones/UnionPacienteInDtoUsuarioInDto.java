package com.Dialisis.DialisisPeritoneal.service.dto.Uniones;

import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.UsuarioInDto;
import lombok.Data;

@Data
public class UnionPacienteInDtoUsuarioInDto {

    private final PacienteInDto pacienteInDto;
    private final UsuarioInDto usuarioInDto;

}

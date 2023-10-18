package com.dialisis.dialisisperitoneal.service.dto.uniones;

import com.dialisis.dialisisperitoneal.service.dto.PacienteInDto;
import com.dialisis.dialisisperitoneal.service.dto.UsuarioInDto;
import lombok.Data;

@Data
public class UnionPacienteInDtoUsuarioInDto {

    private final PacienteInDto pacienteInDto;
    private final UsuarioInDto usuarioInDto;

}

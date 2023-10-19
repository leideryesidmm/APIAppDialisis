package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Especialidad;
import com.dialisis.dialisisperitoneal.service.dto.EspecialidadInDto;
import org.springframework.stereotype.Component;

@Component
public class EspecialidaInDtoToEspecialidad implements IMapper<EspecialidadInDto, Especialidad> {

    @Override
    public Especialidad map(EspecialidadInDto in) {
        Especialidad especialidad=new Especialidad();
        especialidad.setDescripcion(in.getDescripcion());
        return especialidad;
    }
}

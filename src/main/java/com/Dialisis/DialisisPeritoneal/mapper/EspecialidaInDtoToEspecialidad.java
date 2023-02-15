package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Especialidad;
import com.Dialisis.DialisisPeritoneal.service.dto.EspecialidadInDto;
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

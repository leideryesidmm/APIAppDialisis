package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Enfermedad;
import com.Dialisis.DialisisPeritoneal.service.dto.EnfermedadInDto;
import org.springframework.stereotype.Component;

@Component
public class EnfermedadInDtoToEnfermedad implements IMapper<EnfermedadInDto, Enfermedad> {

    @Override
    public Enfermedad map(EnfermedadInDto in) {
        Enfermedad enfermedad=new Enfermedad();
        enfermedad.setDescripcion(in.getDescripcion());
        return enfermedad;
    }
}

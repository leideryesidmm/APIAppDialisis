package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Alergia;
import com.Dialisis.DialisisPeritoneal.service.dto.AlergiaInDto;
import org.springframework.stereotype.Component;

@Component
public class AlergiaInDtoToAlergia implements IMapper<AlergiaInDto, Alergia> {

@Override
    public Alergia map(AlergiaInDto in) {
        Alergia alergia = new Alergia();
        alergia.setNombre(in.getNombre());

        return alergia;
    }
}

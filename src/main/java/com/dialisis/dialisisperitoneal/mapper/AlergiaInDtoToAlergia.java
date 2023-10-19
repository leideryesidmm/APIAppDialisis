package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Alergia;
import com.dialisis.dialisisperitoneal.service.dto.AlergiaInDto;
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

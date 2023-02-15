package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Jornada;
import com.Dialisis.DialisisPeritoneal.service.dto.JornadaInDto;
import org.springframework.stereotype.Component;

@Component
public class JornadaInDtoToJornada implements IMapper<JornadaInDto, Jornada> {

    @Override
    public Jornada map(JornadaInDto in) {
        Jornada jornada=new Jornada();
        jornada.setDescripcion(in.getDescripcion());
        return jornada;
    }
}

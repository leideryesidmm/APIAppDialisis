package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Eps;
import com.Dialisis.DialisisPeritoneal.service.dto.EpsInDto;
import org.springframework.stereotype.Component;

@Component
public class EpsInDtoToEps implements IMapper<EpsInDto, Eps>{

    @Override
    public Eps map(EpsInDto in){
    Eps eps= new Eps();
    eps.setNombre(in.getNombre());
        return eps;
    }
}

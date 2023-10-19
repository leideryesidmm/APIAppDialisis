package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Eps;
import com.dialisis.dialisisperitoneal.service.dto.EpsInDto;
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

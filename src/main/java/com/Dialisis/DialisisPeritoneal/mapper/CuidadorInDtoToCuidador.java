package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cuidador;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Parentesco;
import com.Dialisis.DialisisPeritoneal.service.dto.CuidadorInDto;
import org.springframework.stereotype.Component;

@Component
public class CuidadorInDtoToCuidador implements IMapper<CuidadorInDto, Cuidador> {

    @Override
    public Cuidador map(CuidadorInDto in) {
        Cuidador cuidador=new Cuidador();
        cuidador.setTelefono(in.getTelefono());
        cuidador.setDireccion(in.getDireccion());
        cuidador.setNombre(in.getNombre());
        cuidador.setParentesco(new Parentesco(in.getParentesco()));
        return cuidador;
    }
}

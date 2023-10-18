package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Cuidador;
import com.dialisis.dialisisperitoneal.persistence.entity.Parentesco;
import com.dialisis.dialisisperitoneal.service.dto.CuidadorInDto;
import org.springframework.stereotype.Component;

@Component
public class CuidadorInDtoToCuidador implements IMapper<CuidadorInDto, Cuidador> {

    @Override
    public Cuidador map(CuidadorInDto in) {
        Cuidador cuidador=new Cuidador();
        cuidador.setCedulaCuidador(in.getCedulaCuidador());
        cuidador.setTelefono(in.getTelefono());
        cuidador.setDireccion(in.getDireccion());
        cuidador.setNombre(in.getNombre());
        cuidador.setParentesco(new Parentesco(in.getParentesco()));
        return cuidador;
    }
}

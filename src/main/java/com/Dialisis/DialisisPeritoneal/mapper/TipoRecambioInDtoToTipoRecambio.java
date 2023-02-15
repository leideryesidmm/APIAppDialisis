package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.TipoRecambio;
import com.Dialisis.DialisisPeritoneal.service.dto.TipoRecambioInDto;
import org.springframework.stereotype.Component;

@Component
public class TipoRecambioInDtoToTipoRecambio implements IMapper<TipoRecambioInDto, TipoRecambio> {

    public TipoRecambio map(TipoRecambioInDto in){
        TipoRecambio tipoRecambio= new TipoRecambio();
        tipoRecambio.setDescripcion(in.getDescripcion());
        return tipoRecambio;
    }
}

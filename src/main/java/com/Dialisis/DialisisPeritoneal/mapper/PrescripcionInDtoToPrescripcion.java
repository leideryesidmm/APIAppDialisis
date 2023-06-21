package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Prescripcion;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionInDto;
import org.springframework.stereotype.Component;

@Component
public class PrescripcionInDtoToPrescripcion implements IMapper<PrescripcionInDto, Prescripcion>{

    public Prescripcion map(PrescripcionInDto in){

        Prescripcion prescripcion = new Prescripcion();
        prescripcion.setOrificioSalida(in.getOrificioSalida());
        prescripcion.setNocheSeca(false);
        return prescripcion;
    }
}

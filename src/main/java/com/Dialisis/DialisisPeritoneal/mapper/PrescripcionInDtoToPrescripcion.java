package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Prescripcion;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionInDto;

public class PrescripcionInDtoToPrescripcion implements IMapper<PrescripcionInDto, Prescripcion>{

    public Prescripcion map(PrescripcionInDto in){

        Prescripcion prescripcion = new Prescripcion();
        prescripcion.setOrificioSalida(in.getOrificioSalida());

        return prescripcion;
    }

}

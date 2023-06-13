package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Comorbilidad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Enfermedad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.service.dto.ComorbilidadInDto;
import org.springframework.stereotype.Component;

@Component
public class ComorbilidadInDtoToComorbilidad implements IMapper<ComorbilidadInDto, Comorbilidad> {

    public Comorbilidad map(ComorbilidadInDto in ){
        Comorbilidad comorbilidad = new Comorbilidad();
        comorbilidad.setPaciente(new Paciente(in.getPaciente()));
        comorbilidad.setEnfermedad(new Enfermedad(in.getEnfermedad()));
        comorbilidad.setActivo(true);
        return comorbilidad;
    }
}

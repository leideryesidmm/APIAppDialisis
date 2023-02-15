package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cormobilidad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Enfermedad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.service.dto.CormobilidadInDto;
import org.springframework.stereotype.Component;

@Component
public class CormobilidadInDtoToCormobilidad implements IMapper<CormobilidadInDto, Cormobilidad> {

    public Cormobilidad map(CormobilidadInDto in ){
        Cormobilidad cormobilidad = new Cormobilidad();
        cormobilidad.setPaciente(new Paciente(in.getPaciente()));
        cormobilidad.setEnfermedad(new Enfermedad(in.getEnfermedad()));
        cormobilidad.setActivo(true);
        return cormobilidad;
    }
}

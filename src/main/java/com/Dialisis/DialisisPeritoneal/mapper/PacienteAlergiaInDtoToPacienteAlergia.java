package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Alergia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.PacienteAlergia;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteAlergiaInDto;
import org.springframework.stereotype.Component;

@Component
public class PacienteAlergiaInDtoToPacienteAlergia implements IMapper<PacienteAlergiaInDto, PacienteAlergia> {

    public PacienteAlergia map(PacienteAlergiaInDto in){
        PacienteAlergia pacienteAlergia= new PacienteAlergia();
        pacienteAlergia.setPaciente(new Paciente(in.getPaciente()));
        pacienteAlergia.setAlergia(new Alergia(in.getAlergia()));
        return pacienteAlergia;
    }
}

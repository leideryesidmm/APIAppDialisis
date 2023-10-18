package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Alergia;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.persistence.entity.PacienteAlergia;
import com.dialisis.dialisisperitoneal.service.dto.PacienteAlergiaInDto;
import org.springframework.stereotype.Component;

@Component
public class PacienteAlergiaInDtoToPacienteAlergia implements IMapper<PacienteAlergiaInDto, PacienteAlergia> {

    public PacienteAlergia map(PacienteAlergiaInDto in){
        PacienteAlergia pacienteAlergia= new PacienteAlergia();
        pacienteAlergia.setPaciente(new Paciente(in.getPaciente()));
        pacienteAlergia.setAlergia(new Alergia(in.getAlergia()));
        pacienteAlergia.setActivo(true);
        return pacienteAlergia;
    }
}

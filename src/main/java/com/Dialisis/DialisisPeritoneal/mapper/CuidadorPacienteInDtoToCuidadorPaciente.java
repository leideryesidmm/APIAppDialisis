package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cuidador;
import com.Dialisis.DialisisPeritoneal.persistence.entity.CuidadorPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.service.dto.CuidadorPacienteInDto;
import org.springframework.stereotype.Component;

@Component
public class CuidadorPacienteInDtoToCuidadorPaciente implements IMapper<CuidadorPacienteInDto, CuidadorPaciente> {
    @Override
    public CuidadorPaciente map(CuidadorPacienteInDto in) {
        CuidadorPaciente cuidadorpaciente=new CuidadorPaciente();
        cuidadorpaciente.setCuidador(new Cuidador(in.getCuidador()));
        cuidadorpaciente.setPaciente(new Paciente(in.getPaciente()));
        cuidadorpaciente.setFechaInicio(in.getFecha_ini());
        cuidadorpaciente.setFechaFin(in.getFecha_fin());
        cuidadorpaciente.setActivo(true);
        return cuidadorpaciente;
    }
}

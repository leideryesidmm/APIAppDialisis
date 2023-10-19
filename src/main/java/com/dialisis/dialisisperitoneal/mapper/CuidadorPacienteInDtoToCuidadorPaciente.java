package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Cuidador;
import com.dialisis.dialisisperitoneal.persistence.entity.CuidadorPaciente;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.service.dto.CuidadorPacienteInDto;
import org.springframework.stereotype.Component;

@Component
public class CuidadorPacienteInDtoToCuidadorPaciente implements IMapper<CuidadorPacienteInDto, CuidadorPaciente> {
    @Override
    public CuidadorPaciente map(CuidadorPacienteInDto in) {
        CuidadorPaciente cuidadorpaciente=new CuidadorPaciente();
        cuidadorpaciente.setCuidador(new Cuidador(in.getCuidador()));
        cuidadorpaciente.setPaciente(new Paciente(in.getPaciente()));
        cuidadorpaciente.setFechaInicio(in.getFechaIni());
        cuidadorpaciente.setFechaFin(in.getFechaFin());
        cuidadorpaciente.setActivo(true);
        return cuidadorpaciente;
    }
}

package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.AlimentacionPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Jornada;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.service.dto.AlimentacionPacienteInDto;
import org.springframework.stereotype.Component;

@Component
public class AlimentacionPacienteInDtoToAlimentacionPaciente implements IMapper<AlimentacionPacienteInDto, AlimentacionPaciente>{

    public AlimentacionPaciente map(AlimentacionPacienteInDto in){
        AlimentacionPaciente alimentacionPaciente= new AlimentacionPaciente();
        alimentacionPaciente.setPaciente(new Paciente(in.getPaciente()));
        alimentacionPaciente.setAlimentacion(in.getAlimentacion());
        alimentacionPaciente.setJornada(new Jornada(in.getJornada()));
        alimentacionPaciente.setFecha_hora(in.getFecha_hora());
        alimentacionPaciente.setCantidad(in.getCantidad());
        return alimentacionPaciente;
    }
}

package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medico;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.service.dto.CitaInDto;
import org.springframework.stereotype.Component;

@Component
public class CitaInDtoToCita implements IMapper<CitaInDto, Cita>{

    public Cita map(CitaInDto in){
        Cita cita= new Cita();
        cita.setNombre_medico(in.getNombre_medico());
        cita.setPaciente(new Paciente(in.getPaciente()));
        cita.setLugar(in.getLugar());
        cita.setDireccion(in.getDireccion());
        cita.setFecha(in.getFecha());
        return cita;
    }
}

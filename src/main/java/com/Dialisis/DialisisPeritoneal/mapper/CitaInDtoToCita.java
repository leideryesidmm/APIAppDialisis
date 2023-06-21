package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Especialidad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medico;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.service.dto.CitaInDto;
import org.springframework.stereotype.Component;

@Component
public class CitaInDtoToCita implements IMapper<CitaInDto, Cita>{

    public Cita map(CitaInDto in){
        Cita cita= new Cita();
        cita.setMedico(new Medico(in.getMedico()));
        cita.setPaciente(new Paciente(in.getPaciente()));
        cita.setDireccion(in.getDireccion());
        cita.setFecha(in.getFecha());
        cita.setHora(in.getHora());
        return cita;
    }
}

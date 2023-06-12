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
        cita.setCedulaMedico(in.getCedulaMedico());
        cita.setCedulaPaciente(new Paciente(in.getCedulaPaciente()));
        cita.setClinica(in.getClinica());
        cita.setDireccion(in.getDireccion());
        cita.setFecha(in.getFecha());
        cita.setHora(in.getHora());
        return cita;
    }
}

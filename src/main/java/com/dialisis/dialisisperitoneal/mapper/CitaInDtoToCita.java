package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.service.dto.CitaInDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CitaInDtoToCita implements IMapper<CitaInDto, Cita>{

    public Cita map(CitaInDto in){
        Cita cita= new Cita();
        cita.setMedico(new Medico(in.getMedico()));
        cita.setPaciente(new Paciente(in.getPaciente()));
        cita.setFecha(LocalDateTime.now());
        cita.setHora(LocalDateTime.now());
        cita.setFechaFin(in.getFechaFin());
        cita.setOrificioSalida(in.getOrificioSalida());
        return cita;
    }
}

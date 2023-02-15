package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Clinica;
import com.Dialisis.DialisisPeritoneal.service.dto.ClinicaInDto;
import org.springframework.stereotype.Component;

@Component
public class ClinicaInDtoToClinica implements IMapper<ClinicaInDto, Clinica> {
    public Clinica map(ClinicaInDto in){
        Clinica clinica= new Clinica();
        clinica.setNombre(in.getNombre());
        clinica.setDireccion(in.getDireccion());
        return clinica;
    }
}

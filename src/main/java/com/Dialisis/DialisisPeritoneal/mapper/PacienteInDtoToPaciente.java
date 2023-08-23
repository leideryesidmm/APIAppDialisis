package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import org.springframework.stereotype.Component;

@Component
public class   PacienteInDtoToPaciente implements IMapper<PacienteInDto, Paciente> {
    @Override
    public Paciente map(PacienteInDto in){
        Paciente paciente= new Paciente();
        paciente.setCedula(in.getCedula());
        paciente.setFechaNacimiento(in.getFechaNacimiento());
        paciente.setEps(in.getEps());
        paciente.setAltura(in.getAltura());
        paciente.setPeso(in.getPeso());
        paciente.setPesoSeco(in.getPesoSeco());
        paciente.setDireccion(in.getDireccion());
        paciente.setOcupacion(in.getOcupacion());
        paciente.setTipoSangre(in.getTipoSangre());
        paciente.setRh(in.getRh());
        paciente.setNombre(in.getNombre());
        paciente.setCelular(in.getCelular());
        paciente.setContrasenia(in.getContrasenia());
        paciente.setDiabetes(in.isDiabetes());
        paciente.setHipertension(in.isHipertension());
        paciente.setActivo(in.isActivo());

        return paciente;
    }
}


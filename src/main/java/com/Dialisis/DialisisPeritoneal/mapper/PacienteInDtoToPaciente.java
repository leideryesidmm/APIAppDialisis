package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import org.springframework.stereotype.Component;

@Component
public class PacienteInDtoToPaciente implements IMapper<PacienteInDto, Paciente> {
    @Override
    public Paciente map(PacienteInDto in){
        Paciente paciente= new Paciente();
        paciente.setCedula(in.getCedula());
        paciente.setFechaNacimiento(in.getFechaNacimiento());
        paciente.setEdad(in.getEdad());
        paciente.setEps(in.getEps());
        paciente.setAltura(in.getAltura());
        paciente.setPeso(in.getPeso());
        paciente.setPeso_seco(in.getPeso_seco());
        paciente.setDireccion(in.getDireccion());
        paciente.setOcupacion(in.getOcupacion());
        paciente.setTipo_sangre(in.getTipo_sangre());
        paciente.setRh(in.getRh());
        paciente.setNombre(in.getNombre());
        paciente.setCorreo(in.getCorreo());
        paciente.setCelular(in.getCelular());
        paciente.setContrasenia(in.getContrasenia());

        return paciente;
    }
}


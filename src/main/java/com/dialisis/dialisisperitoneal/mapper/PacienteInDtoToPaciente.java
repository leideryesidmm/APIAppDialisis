package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Eps;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.service.dto.PacienteInDto;
import org.springframework.stereotype.Component;

@Component
public class   PacienteInDtoToPaciente implements IMapper<PacienteInDto, Paciente> {
    @Override
    public Paciente map(PacienteInDto in){
        Paciente paciente= new Paciente();
        paciente.setCedula(in.getCedula());
        paciente.setFechaNacimiento(in.getFechaNacimiento());
        paciente.setEps(new Eps(in.getEps()));
        paciente.setAltura(in.getAltura());
        paciente.setPeso(in.getPeso());
        paciente.setPesoSeco(in.getPesoSeco());
        paciente.setDireccion(in.getDireccion());
        paciente.setOcupacion(in.getOcupacion());
        paciente.setTipoSangre(in.getTipoSangre());
        paciente.setRh(in.getRh());
        paciente.setNombre(in.getNombre());
        paciente.setCelular(in.getCelular());
        paciente.setCorreo(in.getCorreo());
        paciente.setContrasenia(in.getContrasenia());
        paciente.setDiabetes(in.isDiabetes());
        paciente.setHipertension(in.isHipertension());
        paciente.setTipoDocumento(in.getTipoDocumento());
        paciente.setFechaRegistro(in.getFechaRegistro());
        paciente.setActivo(in.isActivo());
        paciente.setCambioContrasenia(in.isCambioContrasenia());
        return paciente;
    }
}


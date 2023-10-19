package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Medico;
import com.dialisis.dialisisperitoneal.service.dto.MedicoInDto;
import org.springframework.stereotype.Component;

@Component
public class MedicoInDtoToMedico implements IMapper<MedicoInDto, Medico> {

    @Override
    public Medico map(MedicoInDto in) {
        Medico medico=new Medico();
        medico.setCedula(in.getCedula());
        medico.setEspecialidad(in.getEspecialidad());
        medico.setProfesion(in.getProfesion());
        medico.setNombre(in.getNombre());
        medico.setCelular(in.getCelular());
        medico.setContrasenia(in.getContrasenia());
        medico.setCorreo(in.getCorreo());
        medico.setFoto(in.getFoto());
        medico.setActivo(in.isActivo());
        medico.setTipoDocumento(in.getTipoDocumento());
        return medico;
    }
}

package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Medico;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoInDto;
import org.springframework.stereotype.Component;

@Component
public class MedicoInDtoToMedico implements IMapper<MedicoInDto, Medico> {

    @Override
    public Medico map(MedicoInDto in) {
        Medico medico=new Medico();
        medico.setCedula(in.getCedula());
        medico.setEspecialidad(in.getEspecialidad());
        medico.setAnios_de_experiencia(in.getAnios_de_experiencia());
        medico.setNombre(in.getNombre());
        medico.setCorreo(in.getCorreo());
        medico.setCelular(in.getCelular());
        medico.setContrasenia(in.getContrasenia());

        return medico;
    }
}

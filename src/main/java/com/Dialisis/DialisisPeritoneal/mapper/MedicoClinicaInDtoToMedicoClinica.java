package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Clinica;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medico;
import com.Dialisis.DialisisPeritoneal.persistence.entity.MedicoClinica;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoClinicaInDto;
import org.springframework.stereotype.Component;

@Component
public class MedicoClinicaInDtoToMedicoClinica implements IMapper<MedicoClinicaInDto, MedicoClinica> {

    public MedicoClinica map(MedicoClinicaInDto in){
        MedicoClinica medicoClinica=new MedicoClinica();
        medicoClinica.setMedico(new Medico(in.getMedico()));
        medicoClinica.setClinica(new Clinica(in.getClinica()));
        return medicoClinica;
    }
}

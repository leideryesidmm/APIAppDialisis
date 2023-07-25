package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteAlergiaInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionDiaInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.RecambioInDto;
import org.springframework.stereotype.Component;

@Component
public class RecambioInDtoToRecambio implements  IMapper<RecambioInDto, Recambio>{

    public Recambio map(RecambioInDto in){
        Recambio recambio= new Recambio();
        recambio.setPrescripcionDia(new PrescripcionDia());
        recambio.setIdRecambio(in.getIdRecambio());
        recambio.setConcentracion(in.getConcentracion());
        recambio.setIntervaloTiempo(in.getIntervaloTiempo());
        return recambio;
    }
}

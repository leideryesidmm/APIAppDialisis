package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.service.dto.RecambioInDto;
import org.springframework.stereotype.Component;

@Component
public class RecambioInDtoToRecambio implements  IMapper<RecambioInDto, Recambio>{

    public Recambio map(RecambioInDto in){
        Recambio recambio= new Recambio();
        recambio.setPrescripcionDia(new PrescripcionDia());
        recambio.setConcentracion(in.getConcentracion());
        recambio.setIntervaloTiempo(in.getIntervaloTiempo());
        return recambio;
    }
}

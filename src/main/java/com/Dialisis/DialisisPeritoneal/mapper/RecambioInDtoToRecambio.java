package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteAlergiaInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.RecambioInDto;
import org.springframework.stereotype.Component;

@Component
public class RecambioInDtoToRecambio implements  IMapper<RecambioInDto, Recambio>{

    public Recambio map(RecambioInDto in){
        Recambio recambio= new Recambio();
        recambio.setPrescripcionDia(new PrescripcionDia(in.getPrescripcionDia()));
        recambio.setIdRecambio(in.getIdRecambio());
        recambio.setFecha(in.getFecha());
        recambio.setHora(in.getHora());
        recambio.setConcentración(in.getConcentración());
        recambio.setDrenajeDialisis(in.getDrenajeDialisis());
        recambio.setRealizada(false);
        recambio.setEstadoLiquido(in.getEstadoLiquido());
        return recambio;
    }
}

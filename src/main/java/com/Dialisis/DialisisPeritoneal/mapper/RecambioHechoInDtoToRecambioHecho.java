package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.RecambioHecho;
import com.Dialisis.DialisisPeritoneal.service.dto.RecambioHechoInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.RecambioInDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RecambioHechoInDtoToRecambioHecho implements  IMapper<RecambioHechoInDto, RecambioHecho> {

    @Override
    public RecambioHecho map(RecambioHechoInDto in) {
        RecambioHecho recambioHecho= new RecambioHecho();
        recambioHecho.setDrenajeDialisis(in.getDrenajeDialisis());
        recambioHecho.setRecambio(new Recambio(in.getRecambio()));
        recambioHecho.setHora_ini(in.getHora_ini());
        recambioHecho.setHora_fin(in.getHora_fin());
        recambioHecho.setFecha(LocalDateTime.now());
        recambioHecho.setOrificioSalida(in.getOrificioSalida());
        recambioHecho.setCaracteristicaLiquido(in.getCaracteristicaLiquido());
        return recambioHecho;
    }
}

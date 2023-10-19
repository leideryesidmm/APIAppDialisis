package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Recambio;
import com.dialisis.dialisisperitoneal.persistence.entity.RecambioHecho;
import com.dialisis.dialisisperitoneal.service.dto.RecambioHechoInDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RecambioHechoInDtoToRecambioHecho implements  IMapper<RecambioHechoInDto, RecambioHecho> {

    @Override
    public RecambioHecho map(RecambioHechoInDto in) {
        RecambioHecho recambioHecho= new RecambioHecho();
        recambioHecho.setLiquidoEntrante(in.getLiquidoEntrante());
        recambioHecho.setDrenajeDialisis(in.getDrenajeDialisis());
        recambioHecho.setRecambio(new Recambio(in.getRecambio()));
        recambioHecho.setHoraIni(in.getHoraIni());
        recambioHecho.setHoraFin(in.getHoraFin());
        recambioHecho.setFechaReal(in.getFechaReal());
        recambioHecho.setFecha(LocalDateTime.now());
        recambioHecho.setOrificioSalida(in.getOrificioSalida());
        recambioHecho.setCaracteristicaLiquido(in.getCaracteristicaLiquido());
        return recambioHecho;
    }
}

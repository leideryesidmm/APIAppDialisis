package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Cita;
import com.dialisis.dialisisperitoneal.persistence.entity.VisitaEspecialista;
import com.dialisis.dialisisperitoneal.service.dto.VisitaEspecialistaInDto;
import org.springframework.stereotype.Component;

@Component
public class   VisitaEspecialistaInDtoToVisitaEspecialista implements IMapper<VisitaEspecialistaInDto, VisitaEspecialista> {

    @Override
    public VisitaEspecialista map(VisitaEspecialistaInDto in){
        VisitaEspecialista visita= new VisitaEspecialista();
        visita.setCita(new Cita(in.getCita()));
        visita.setNefrologia(in.isNefrologia());
        visita.setEnfermeria(in.isEnfermeria());
        visita.setPsicologia(in.isPsicologia());
        visita.setNutricion(in.isNutricion());
        visita.setTrabajoSocial(in.isTrabajoSocial());
        visita.setAuxiliarAdmisiones(in.isAuxiliarAdmisiones());
        visita.setFarmacia(in.isFarmacia());
        visita.setEntrenamiento(in.isEntrenamiento());
        visita.setReentrenamiento(in.isReentrenamiento());
        visita.setVisitaDomiciliaria(in.isVisitaDomiciliaria());

        return visita;
}
}

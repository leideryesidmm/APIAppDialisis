package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.VisitaEspecialista;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.VisitaEspecialistaInDto;
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

        return visita;
}
}

package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionDiaInDto;
import org.springframework.stereotype.Component;

@Component
public class PrescripcionDiaToPrescripcionDia implements IMapper<PrescripcionDiaInDto, PrescripcionDia> {
        public PrescripcionDia map(PrescripcionDiaInDto in){
        PrescripcionDia prescripcionDia= new PrescripcionDia();
        prescripcionDia.setLunes(in.isLunes());
        prescripcionDia.setMartes(in.isMartes());
        prescripcionDia.setMiercoles(in.isMiercoles());
        prescripcionDia.setJueves(in.isJueves());
        prescripcionDia.setViernes(in.isViernes());
        prescripcionDia.setSabado(in.isSabado());
        prescripcionDia.setDomingo(in.isDomingo());
        prescripcionDia.setNocheSeca(in.isNocheSeca());
        prescripcionDia.setCita(new Cita(in.getCita()));
        return prescripcionDia;
    }
}

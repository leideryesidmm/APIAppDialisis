package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.ChequeoMensual;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.service.dto.ChequeoMensualInDto;
import org.springframework.stereotype.Component;

@Component
public class ChequeoMensualInDtoToChequeoMensual implements IMapper<ChequeoMensualInDto, ChequeoMensual> {


    @Override
    public ChequeoMensual map(ChequeoMensualInDto in) {
        ChequeoMensual chequeoMensual=new ChequeoMensual();
        chequeoMensual.setCita(new Cita(in.getCita()));
        chequeoMensual.setTensionArterial(in.getTensionArterial());
        chequeoMensual.setColesterolTotal(in.getColesterolTotal());
        chequeoMensual.setHemoglobina(in.getHemoglobina());
        chequeoMensual.setTrigliceridos(in.getTrigliceridos());
        chequeoMensual.setGlicemia(in.getGlicemia());
        chequeoMensual.setHdl(in.getHdl());
        chequeoMensual.setLdh(in.getLdh());
        chequeoMensual.setPotasio(in.getPotasio());
        chequeoMensual.setFosforo(in.getFosforo());
        chequeoMensual.setNitrogenoUreico(in.getNitrogenoUreico());
        chequeoMensual.setPeso(in.getPeso());
        chequeoMensual.setPeso_seco(in.getPeso_seco());
        chequeoMensual.setKtv(in.getKtv());
        chequeoMensual.setGlucosa(in.getGlucosa());
        chequeoMensual.setCreatinina(in.getCreatinina());
        return chequeoMensual;
    }
}

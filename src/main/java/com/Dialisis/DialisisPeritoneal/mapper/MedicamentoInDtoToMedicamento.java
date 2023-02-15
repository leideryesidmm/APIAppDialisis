package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Medicamento;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicamentoInDto;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoInDtoToMedicamento implements IMapper<MedicamentoInDto, Medicamento>{
    public Medicamento map(MedicamentoInDto in){
        Medicamento medicamento = new Medicamento();
        medicamento.setConcentracion(in.getConcentracion());
        medicamento.setDescripcion(in.getDescripcion());
        medicamento.setVia_Administracion(in.getVia_Administracion());
        medicamento.setNombre(in.getNombre());

        return medicamento;
    }

}
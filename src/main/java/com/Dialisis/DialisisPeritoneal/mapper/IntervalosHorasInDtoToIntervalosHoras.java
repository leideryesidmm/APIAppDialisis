package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.IntervalosHoras;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Prescripcion;
import com.Dialisis.DialisisPeritoneal.service.dto.IntervalosHorasInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicamentoInDto;

public class IntervalosHorasInDtoToIntervalosHoras implements IMapper<IntervalosHorasInDto, IntervalosHoras>{
    @Override
    public IntervalosHoras map(IntervalosHorasInDto in) {
        IntervalosHoras intervalosHoras=new IntervalosHoras();
        intervalosHoras.setPrescripcion(new Prescripcion(in.getPrescripcion()));
        intervalosHoras.setDescripcion(in.getDescripcion());
        return intervalosHoras;
    }
}

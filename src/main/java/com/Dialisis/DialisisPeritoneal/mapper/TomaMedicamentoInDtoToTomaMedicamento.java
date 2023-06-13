package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.ProgramarMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.TomaMedicamento;
import com.Dialisis.DialisisPeritoneal.service.dto.TomaMedicamentoInDto;
import org.springframework.stereotype.Component;

@Component
public class TomaMedicamentoInDtoToTomaMedicamento implements IMapper<TomaMedicamentoInDto, TomaMedicamento>{

    public TomaMedicamento map(TomaMedicamentoInDto in){
        TomaMedicamento tomaMedicamento= new TomaMedicamento();
        tomaMedicamento.setProgramarMedicamento(new ProgramarMedicamento(in.getProgramarMedicamento()));
        tomaMedicamento.setHora(in.getHora());
        tomaMedicamento.setTomado(false);
        return tomaMedicamento;
    }
}

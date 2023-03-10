package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.FormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Parentesco;
import com.Dialisis.DialisisPeritoneal.persistence.entity.ProgramarMedicamento;
import com.Dialisis.DialisisPeritoneal.service.dto.ProgramarMedicamentoInDto;
import org.springframework.stereotype.Component;

@Component
public class ProgramarMedicamentoInDtoToProgramarMedicamento implements IMapper<ProgramarMedicamentoInDto, ProgramarMedicamento>{

    public ProgramarMedicamento map(ProgramarMedicamentoInDto in){
        ProgramarMedicamento programarMedicamento= new ProgramarMedicamento();
        programarMedicamento.setFormulaMedicamento(new FormulaMedicamento(in.getId_formula_medicamento()));
        programarMedicamento.setFecha_ini(in.getFecha_ini());
        programarMedicamento.setFecha_fin(in.getFecha_fin());
        return programarMedicamento;
    }
}

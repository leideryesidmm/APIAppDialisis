package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.FormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.service.dto.FormulaMedicamentoInDto;
import org.springframework.stereotype.Component;

@Component
public class FormulaMedicamentoInDtoToFormulaMedicamento implements IMapper<FormulaMedicamentoInDto, FormulaMedicamento> {

    public FormulaMedicamento map(FormulaMedicamentoInDto in){
        FormulaMedicamento formulaMedicamento=new FormulaMedicamento();
        formulaMedicamento.setPaciente(new Paciente(in.getPaciente()));
        formulaMedicamento.setMedicamento(new Medicamento(in.getMedicamento()));
        formulaMedicamento.setIntervaloTiempo(in.getIntervaloTiempo());
        formulaMedicamento.setTomas(in.getTomas());
        formulaMedicamento.setDosis(in.getDosis());
        return formulaMedicamento;
    }


}

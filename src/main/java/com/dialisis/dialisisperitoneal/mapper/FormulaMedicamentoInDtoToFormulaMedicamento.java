package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.service.dto.FormulaMedicamentoInDto;
import org.springframework.stereotype.Component;

@Component
public class FormulaMedicamentoInDtoToFormulaMedicamento implements IMapper<FormulaMedicamentoInDto, FormulaMedicamento> {

    public FormulaMedicamento map(FormulaMedicamentoInDto in){
        FormulaMedicamento formulaMedicamento=new FormulaMedicamento();
        formulaMedicamento.setPaciente(new Paciente(in.getPaciente()));
        formulaMedicamento.setTomas(in.getTomas());
        formulaMedicamento.setNombre(in.getNombre());
        formulaMedicamento.setConcentracion(in.getConcentracion());
        formulaMedicamento.setViaAdministracion(new ViaAdministracion(in.getViaAdministracion()));
        formulaMedicamento.setDescripcion(in.getDescripcion());
        formulaMedicamento.setFechaIni(in.getFechaIni());
        formulaMedicamento.setFechaFin(in.getFechaFin());
        formulaMedicamento.setRecetado(in.isRecetado());
        return formulaMedicamento;
    }


}

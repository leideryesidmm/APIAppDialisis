package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.FormulaMedicamentoInDtoToFormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.FormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.repository.FormulaMedicamentoRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.FormulaMedicamentoInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class FormulaMedicamentoService {

    private final FormulaMedicamentoRepository repository;
    private final FormulaMedicamentoInDtoToFormulaMedicamento mapper;

    public FormulaMedicamentoService(FormulaMedicamentoRepository repository, FormulaMedicamentoInDtoToFormulaMedicamento mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FormulaMedicamento crearFormulaMedicamento(FormulaMedicamentoInDto formulaMedicamentoInDto){
        FormulaMedicamento formulaMedicamento= mapper.map(formulaMedicamentoInDto);
        return this.repository.save(formulaMedicamento);
    }

    public List<FormulaMedicamento> findAll(){
        return this.repository.findAll();
    }

    public FormulaMedicamento findById(int id_formula_medicamento){
        return this.repository.findById(id_formula_medicamento);
    }
    @Transactional
    public void actualizarFormula(int id_formula_medicamento, int medicamento,int intervalo_tiempo, int tomas, int dosis){
        this.repository.actualizarFormula(id_formula_medicamento,medicamento,intervalo_tiempo,tomas,dosis);
    }
    public  List<FormulaMedicamento> findAllByCita(int cita){
        Cita citaobj=new Cita();
        citaobj.setId_cita(cita);
        return this.repository.findAllByCita(citaobj);
    }
}

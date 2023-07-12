package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.FormulaMedicamentoInDtoToFormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Cormobilidad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.FormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.repository.FormulaMedicamentoRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.FormulaMedicamentoInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        Optional<FormulaMedicamento> optionalFormulaMedicamento = this.repository.findById(id_formula_medicamento);
        if (optionalFormulaMedicamento.isEmpty()) {
            throw new ToDoExceptions("Formula Medicamento no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalFormulaMedicamento.get();
    }
    @Transactional
    public void actualizarFormula(int id_formula_medicamento,FormulaMedicamentoInDto formulaMedicamentoInDto){
        FormulaMedicamento formulaMedicamento= mapper.map(formulaMedicamentoInDto);
        formulaMedicamento.setIdFormulaMedicamento(id_formula_medicamento);
        this.repository.save(formulaMedicamento);
    }
    public  List<FormulaMedicamento> findAllByPaciente(String pacientein){
        Paciente paciente=new Paciente(pacientein);
        return this.repository.findAllByPaciente(paciente);
    }
}

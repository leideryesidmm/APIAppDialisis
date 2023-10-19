package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.exceptions.ToDoExceptions;
import com.dialisis.dialisisperitoneal.mapper.FormulaMedicamentoInDtoToFormulaMedicamento;
import com.dialisis.dialisisperitoneal.persistence.entity.FormulaMedicamento;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.persistence.repository.FormulaMedicamentoRepository;
import com.dialisis.dialisisperitoneal.service.dto.FormulaMedicamentoInDto;
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

    public FormulaMedicamento findById(int idFormulaMedicamento){
        Optional<FormulaMedicamento> optionalFormulaMedicamento = this.repository.findById(idFormulaMedicamento);
        if (optionalFormulaMedicamento.isEmpty()) {
            throw new ToDoExceptions("Formula Medicamento no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalFormulaMedicamento.get();
    }
    @Transactional
    public void actualizarFormula(int idFormulaMedicamento,FormulaMedicamentoInDto formulaMedicamentoInDto){
        FormulaMedicamento formulaMedicamento= mapper.map(formulaMedicamentoInDto);
        formulaMedicamento.setIdFormulaMedicamento(idFormulaMedicamento);
        this.repository.save(formulaMedicamento);
    }
    public  List<FormulaMedicamento> findAllByPaciente(String pacientein){
        Paciente paciente=new Paciente(pacientein);
        return this.repository.findAllByPaciente(paciente);
    }
    public void deleteById(int idMedicamento){
        Optional<FormulaMedicamento> optionalCita = this.repository.findById(idMedicamento);
        if (optionalCita.isEmpty()) {
            throw new ToDoExceptions("Cita no encontrada", HttpStatus.NOT_FOUND);
        }else {
            this.repository.deleteById(idMedicamento);
        }
    }
}

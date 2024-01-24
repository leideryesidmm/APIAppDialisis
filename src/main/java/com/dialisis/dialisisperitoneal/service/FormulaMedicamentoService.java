package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.exceptions.ToDoExceptions;
import com.dialisis.dialisisperitoneal.mapper.FormulaMedicamentoInDtoToFormulaMedicamento;
import com.dialisis.dialisisperitoneal.persistence.entity.FormulaMedicamento;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.persistence.repository.FormulaMedicamentoRepository;
import com.dialisis.dialisisperitoneal.service.dto.FormulaMedicamentoInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FormulaMedicamentoService {

    private final FormulaMedicamentoRepository repository;
    private final FormulaMedicamentoInDtoToFormulaMedicamento mapper;
    private final EncryptionService encryptionService;


    public FormulaMedicamentoService(FormulaMedicamentoRepository repository, FormulaMedicamentoInDtoToFormulaMedicamento mapper, EncryptionService encryptionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }

    public FormulaMedicamento crearFormulaMedicamento(FormulaMedicamento formulaMedicamento){
        formulaMedicamento=encryptionService.getEncFrontend().getFormulaMedicamento().desencriptar(formulaMedicamento);
        FormulaMedicamento formulaF=encryptionService.getEncBackend().getFormulaMedicamento().encriptar(formulaMedicamento);
        FormulaMedicamento formulaMedB=this.repository.save(formulaF);
        FormulaMedicamento formulaMedBack=encryptionService.getEncBackend().getFormulaMedicamento().desencriptar(formulaMedB);
        FormulaMedicamento formulaMedFront=encryptionService.getEncFrontend().getFormulaMedicamento().desencriptar(formulaMedBack);
        return formulaMedFront;
    }

    public List<FormulaMedicamento> findAll(){
        List<FormulaMedicamento> formulaMedicamentos= this.repository.findAll();
        for (int i = 0; i < formulaMedicamentos.size(); i++) {
            FormulaMedicamento formulaMedicamento=formulaMedicamentos.get(i);
            formulaMedicamento=encryptionService.getEncBackend().getFormulaMedicamento().desencriptar(formulaMedicamento);
            formulaMedicamento=encryptionService.getEncFrontend().getFormulaMedicamento().encriptar(formulaMedicamento);
            formulaMedicamentos.set(i,formulaMedicamento);
        }
        return formulaMedicamentos;
    }

    public FormulaMedicamento findById(int idFormulaMedicamento){
        Optional<FormulaMedicamento> optionalFormulaMedicamento = this.repository.findById(idFormulaMedicamento);
        if (optionalFormulaMedicamento.isEmpty()) {
            throw new ToDoExceptions("Formula Medicamento no encontrada", HttpStatus.NOT_FOUND);
        }
        FormulaMedicamento formulaMedicamento= optionalFormulaMedicamento.get();
        formulaMedicamento=encryptionService.getEncBackend().getFormulaMedicamento().desencriptar(formulaMedicamento);
        formulaMedicamento=encryptionService.getEncFrontend().getFormulaMedicamento().encriptar(formulaMedicamento);
        return formulaMedicamento;

    }
    @Transactional
    public void actualizarFormula(int idFormulaMedicamento,FormulaMedicamentoInDto formulaMedicamentoInDto){
        FormulaMedicamento formulaMedicamento= mapper.map(formulaMedicamentoInDto);
        formulaMedicamento.setIdFormulaMedicamento(idFormulaMedicamento);
        formulaMedicamento=encryptionService.getEncFrontend().getFormulaMedicamento().desencriptar(formulaMedicamento);
        formulaMedicamento=encryptionService.getEncBackend().getFormulaMedicamento().encriptar(formulaMedicamento);
        this.repository.save(formulaMedicamento);
    }
    public  List<FormulaMedicamento> findAllByPaciente(String pacientein){
        Paciente paciente=new Paciente(pacientein);
        paciente=encryptionService.getEncFrontend().getPaciente().desencriptar(paciente);
        paciente=encryptionService.getEncBackend().getPaciente().encriptar(paciente);
        List<FormulaMedicamento> formulaMedicamentos=this.repository.findAllByPaciente(paciente);
        for (int i = 0; i < formulaMedicamentos.size(); i++) {
            FormulaMedicamento formulaMedicamento=formulaMedicamentos.get(i);
            formulaMedicamento=encryptionService.getEncBackend().getFormulaMedicamento().desencriptar(formulaMedicamento);
            formulaMedicamento=encryptionService.getEncFrontend().getFormulaMedicamento().encriptar(formulaMedicamento);
            formulaMedicamentos.set(i,formulaMedicamento);
        }
        return formulaMedicamentos;
    }
    public void deleteById(int idMedicamento){
        Optional<FormulaMedicamento> optionalFormulaMedicamento = this.repository.findById(idMedicamento);
        if (optionalFormulaMedicamento.isEmpty()) {
            throw new ToDoExceptions("Formula no encontrada", HttpStatus.NOT_FOUND);
        }else {
            this.repository.deleteById(idMedicamento);
        }
    }
}

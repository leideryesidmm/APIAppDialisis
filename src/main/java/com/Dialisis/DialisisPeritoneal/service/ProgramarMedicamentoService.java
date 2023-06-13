package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.ProgramarMedicamentoInDtoToProgramarMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.ProgramarMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.repository.ProgramarMedicamentoRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.ProgramarMedicamentoInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramarMedicamentoService {
    private final ProgramarMedicamentoRepository repository;
    private final ProgramarMedicamentoInDtoToProgramarMedicamento mapper;

    public ProgramarMedicamentoService(ProgramarMedicamentoRepository repository, ProgramarMedicamentoInDtoToProgramarMedicamento mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProgramarMedicamento crearProgramarMedicamento(ProgramarMedicamentoInDto programarMedicamentoInDto){
        ProgramarMedicamento programarMedicamento= mapper.map(programarMedicamentoInDto);
        return this.repository.save(programarMedicamento);
    }

    public List<ProgramarMedicamento> findAll(){
        return this.repository.findAll();
    }
    public ProgramarMedicamento findById(int idProgramarMedicamento){
        Optional<ProgramarMedicamento> optionalProgramarMedicamento= this.repository.findById(idProgramarMedicamento);
        if (optionalProgramarMedicamento.isEmpty()) {
            throw new ToDoExceptions("Programcion de medicamento no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalProgramarMedicamento.get();
    }
    @Transactional
    public void actualizarProgramarMedicamento(int idProgramarMedicamento, Date fechaInicio, Date fechaFin){
        this.repository.actualizarProgramarMedicamento(idProgramarMedicamento,fechaInicio,fechaFin);
    }
}

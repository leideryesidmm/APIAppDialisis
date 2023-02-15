package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.ProgramarMedicamentoInDtoToProgramarMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.ProgramarMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.repository.ProgramarMedicamentoRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.ProgramarMedicamentoInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
    public ProgramarMedicamento findById(int id_programar_medicamento){
        return this.repository.findById(id_programar_medicamento);
    }
    @Transactional
    public void actualizarProgramarMedicamento(int id_programar_medicamento, Date fecha_ini, Date fecha_fin){
        this.repository.actualizarProgramarMedicamento(id_programar_medicamento,fecha_ini,fecha_fin);
    }
}

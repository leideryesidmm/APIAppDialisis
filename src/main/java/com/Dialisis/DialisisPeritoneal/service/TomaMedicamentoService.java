package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.TomaMedicamentoInDtoToTomaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.FormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.ProgramarMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.TomaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.repository.TomaMedicamentoRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.TomaMedicamentoInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TomaMedicamentoService {

    private final TomaMedicamentoRepository repository;
    private final TomaMedicamentoInDtoToTomaMedicamento mapper;

    public TomaMedicamentoService(TomaMedicamentoRepository repository, TomaMedicamentoInDtoToTomaMedicamento mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private TomaMedicamento crearTomaMedicamento(TomaMedicamentoInDto tomaMedicamentoInDto){
        TomaMedicamento tomaMedicamento= mapper.map(tomaMedicamentoInDto);
        return this.repository.save(tomaMedicamento);
    }

    public List<TomaMedicamento> crearTomas(ProgramarMedicamento programarMedicamento, FormulaMedicamento formulaMedicamento){
        List<TomaMedicamento> tomasMedicamento=new ArrayList<>();
        LocalDateTime tiempo=programarMedicamento.getFechaInicio();
        for(int i =0; i<formulaMedicamento.getTomas();i++){
            TomaMedicamentoInDto tomaMedicamentoInDto=new TomaMedicamentoInDto();
            tomaMedicamentoInDto.setProgramar_medicamento(programarMedicamento.getIdProgramarMedicamento());
            tomaMedicamentoInDto.setHora(tiempo);
            tiempo=tiempo.plusHours(formulaMedicamento.getIntervaloTiempo());
            tomaMedicamentoInDto.setTomado(false);
            tomasMedicamento.add(crearTomaMedicamento(tomaMedicamentoInDto));
        }
        return tomasMedicamento;
    }
    public List<TomaMedicamento> findAll(){
        return this.repository.findAll();
    }

    public TomaMedicamento findById(int id_tipo_medicamento){
        Optional<TomaMedicamento> optionalTomaMedicamento= this.repository.findById(id_tipo_medicamento);
        if (optionalTomaMedicamento.isEmpty()) {
            throw new ToDoExceptions("Toma de medicamento no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalTomaMedicamento.get();
    }

    @Transactional
    public void actualizarTomaMedicamento(int id_tipo_medicamento, Date hora, boolean tomado){
        this.repository.actualizarTomaMedicamento(id_tipo_medicamento,hora,tomado);
    }
}

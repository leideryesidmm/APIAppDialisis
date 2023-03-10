package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.PacienteAlergiaInDtoToPacienteAlergia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.PacienteAlergia;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PacienteAlergiaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteAlergiaInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteAlergiaService {

    private final PacienteAlergiaRepository repository;
    private final PacienteAlergiaInDtoToPacienteAlergia mapper;

    public PacienteAlergiaService(PacienteAlergiaRepository repository, PacienteAlergiaInDtoToPacienteAlergia mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PacienteAlergia crearPacienteAlergia(PacienteAlergiaInDto pacienteAlergiaInDto){
        PacienteAlergia pacienteAlergia= mapper.map(pacienteAlergiaInDto);
        return this.repository.save(pacienteAlergia);
    }

    public List<PacienteAlergia> findAll(){
        return this.repository.findAll();
    }

    public PacienteAlergia findById(int id_paciente_alergia){
        Optional<PacienteAlergia> optionalPacienteAlergia= this.repository.findById(id_paciente_alergia);
        if (optionalPacienteAlergia.isEmpty()) {
            throw new ToDoExceptions("Alergia de paciente no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalPacienteAlergia.get();
    }
    @Transactional
    public void actualizarPacienteAlergia(int id_paciente_alergia, long paciente, int alergia, boolean activa){
        this.repository.actualizarPacienteAlergia(id_paciente_alergia,paciente,alergia, activa);
    }
    public List<PacienteAlergia> findAllByPaciente(long cedula){
        return this.repository.findAllByPaciente(cedula);
    }
    public PacienteAlergia findAlergiaPorPaciente(long cedula,int idPacienteAlergia){
        return this.repository.findAlergiaPaciente(cedula,idPacienteAlergia);
    }
    @Transactional
    public void inactivarAlergia(long cedula,int idPacienteAlergia){
        this.repository.inactivarAlergia(cedula,idPacienteAlergia);
    }
    @Transactional
    public void activarAlergia(long cedula,int id_aAlergia){
        this.repository.activarAlergia(cedula,id_aAlergia);
    }

    public List<PacienteAlergia> findAlergiasPasadas(long cedula){
        return this.repository.findAlergiasPasadas(cedula);
    }
}

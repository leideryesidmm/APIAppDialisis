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

    public PacienteAlergia findById(int idPacienteAlergia){
        Optional<PacienteAlergia> optionalPacienteAlergia= this.repository.findById(idPacienteAlergia);
        if (optionalPacienteAlergia.isEmpty()) {
            throw new ToDoExceptions("Alergia de paciente no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalPacienteAlergia.get();
    }
    @Transactional
    public void actualizarPacienteAlergia(int idPacienteAlergia, String paciente, int alergia, boolean activa){
        this.repository.actualizarPacienteAlergia(idPacienteAlergia,paciente,alergia, activa);
    }
    public List<PacienteAlergia> findAllByPaciente(String cedula){
        return this.repository.findAllByPaciente(cedula);
    }
    public PacienteAlergia findAlergiaPorPaciente(String cedula,int idPacienteAlergia){
        return this.repository.findAlergiaPaciente(cedula,idPacienteAlergia);
    }
    @Transactional
    public void inactivarAlergia(String cedula,int idPacienteAlergia){
        this.repository.inactivarAlergia(cedula,idPacienteAlergia);
    }
    @Transactional
    public void activarAlergia(String cedula,int idAlergia){
        this.repository.activarAlergia(cedula,idAlergia);
    }

    public List<PacienteAlergia> findAlergiasPasadas(String cedula){
        return this.repository.findAlergiasPasadas(cedula);
    }
}

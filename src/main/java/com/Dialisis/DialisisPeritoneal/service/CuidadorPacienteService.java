package com.Dialisis.DialisisPeritoneal.service;
import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.CuidadorPacienteInDtoToCuidadorPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.CuidadorPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.repository.CuidadorPacienteRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.CuidadorPacienteInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CuidadorPacienteService {
    private final CuidadorPacienteRepository repository;
    private final CuidadorPacienteInDtoToCuidadorPaciente mapper;

    public CuidadorPacienteService(CuidadorPacienteRepository repository, CuidadorPacienteInDtoToCuidadorPaciente mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CuidadorPaciente crearCuidadorPaciente(CuidadorPacienteInDto cuidadorpacienteInDto){
        CuidadorPaciente cuidadorpaciente=this.mapper.map(cuidadorpacienteInDto);
        return this.repository.save(cuidadorpaciente);
    }
    public List<CuidadorPaciente> findAll(){
        return this.repository.findAll();
    }

    public List<CuidadorPaciente> findAllByPaciente(String paciente){
        return this.repository.findAllByPaciente(new Paciente(paciente));
    }

    public CuidadorPaciente findById(int idCuidadorPaciente){
        Optional<CuidadorPaciente> optionalCuidadorPaciente = this.repository.findById(idCuidadorPaciente);
        if (optionalCuidadorPaciente.isEmpty()) {
            throw new ToDoExceptions("Comorbilidad no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalCuidadorPaciente.get();
    }

    public CuidadorPaciente findCuidadorActivo(String cedula){
        return this.repository.findCuidadorActivo(cedula);
    }
    public void actualizarCuidadorPaciente(int idCuidadorPaciente, Date fechaInicio,Date fechaFin, boolean activo){
        this.repository.actualizarCuidadorPaciente(idCuidadorPaciente,fechaInicio,fechaFin, activo);
    }
    @Transactional
    public void inactivarCuidador(int idCuidadorPaciente){
        LocalDate fechaFin= LocalDate.now();
        this.repository.inactivarCuidador(idCuidadorPaciente,fechaFin);
    }

}

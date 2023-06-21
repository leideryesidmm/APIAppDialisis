package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.PacienteInDtoToPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PacienteRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;
    private final PacienteInDtoToPaciente mapper;
    public PacienteService(PacienteRepository repository, PacienteInDtoToPaciente mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public Paciente crearPaciente(PacienteInDto pacienteInDto){
        if(pacienteInDto.getPeso()<pacienteInDto.getPesoSeco()){
            throw new ToDoExceptions("Peso seco debe ser menor a peso", HttpStatus.NOT_FOUND);
        }

        Paciente paciente = mapper.map(pacienteInDto);
        System.out.println(paciente.getFechaNacimiento());
        return this.repository.save(paciente);
    }
    public List <Paciente> findAll(){
        return this.repository.findAll();
    }

    public Paciente findByCedula(String cedula){
        return this.repository.findByCedula(cedula);
    }
    @Transactional
    public void actualizarDatosPaciente(String cedula, String eps, int altura, double peso, double peso_seco, String direccion, String ocupacion){
       if(peso<peso_seco){
           throw new ToDoExceptions("Peso seco debe ser menor a peso", HttpStatus.BAD_REQUEST);
       }
        this.repository.actualizarDatosPaciente(cedula,eps,altura,peso,peso_seco,direccion,ocupacion);
    }
}
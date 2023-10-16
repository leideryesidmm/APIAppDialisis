package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.PacienteInDtoToPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.CuidadorPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.FormulaMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PacienteRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.FormulaMedicamentoInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;
    private final PacienteInDtoToPaciente mapper;

    public PacienteService(PacienteRepository repository, PacienteInDtoToPaciente mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public Paciente crearPaciente(PacienteInDto pacienteInDto) {
        if (pacienteInDto.getPeso() < pacienteInDto.getPesoSeco()) {

            throw new ToDoExceptions("Peso seco debe ser menor a peso", HttpStatus.NOT_FOUND);
        }

        Paciente paciente = mapper.map(pacienteInDto);
        return this.repository.save(paciente);
    }

    public List<Paciente> findAll() {
        return this.repository.findAll();
    }

    public Paciente findByCedula(String cedula) {

        return this.repository.findByCedula(cedula);
    }


    @Transactional
    public void actualizarDatosPaciente(PacienteInDto pacienteInDto, Paciente paciente) {
        Paciente pac= mapper.map(pacienteInDto);
        pac.setCedula(pacienteInDto.getCedula());
        if(paciente.getFoto()!=null) {
            pac.setFoto(paciente.getFoto());
        }
        this.repository.save(pac);
    }


    public List<Paciente> findPacientesActivos() {
        return this.repository.findPacientesActivos();
    }

    public List<Paciente> findPacientesInactivos(String cedula) {
        return this.repository.findPacientesInactivos(cedula);
    }

}
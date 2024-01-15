package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.exceptions.ToDoExceptions;
import com.dialisis.dialisisperitoneal.mapper.PacienteInDtoToPaciente;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.persistence.repository.PacienteRepository;
import com.dialisis.dialisisperitoneal.service.dto.PacienteInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository repository;
    private final PacienteInDtoToPaciente mapper;

    public PacienteService(PacienteRepository repository, PacienteInDtoToPaciente mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public Paciente crearPaciente(PacienteInDto pacienteInDto) {
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
    public List<Object[]> findAllPacientesDatos(){
        System.out.println(this.repository.findAllPacientesDatos());
        return this.repository.findAllPacientesDatos();

    }

}
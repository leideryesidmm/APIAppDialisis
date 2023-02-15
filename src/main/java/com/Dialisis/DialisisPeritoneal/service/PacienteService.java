package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.PacienteInDtoToPaciente;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Paciente;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PacienteRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.PacienteInDto;
import org.springframework.stereotype.Service;

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
        Paciente paciente = mapper.map(pacienteInDto);
        return this.repository.save(paciente);
    }
    public List <Paciente> findAll(){
        return this.repository.findAll();
    }

    public Paciente findByCedula(Long cedula){
        return this.repository.findById(new Paciente(cedula));
    }
    @Transactional
    public void actualizarDatosPaciente(Long cedula, String eps, int altura, int peso, int peso_seco, String direccion, String ocupacion){
        this.repository.actualizarDatosPaciente(cedula,eps,altura,peso,peso_seco,direccion,ocupacion);
    }
}
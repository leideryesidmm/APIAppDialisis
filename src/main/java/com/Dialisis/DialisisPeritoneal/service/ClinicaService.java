package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.ClinicaInDtoToClinica;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Clinica;
import com.Dialisis.DialisisPeritoneal.persistence.repository.ClinicaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.ClinicaInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class ClinicaService {

    private final ClinicaRepository repository;
    private final ClinicaInDtoToClinica mapper;

    public ClinicaService(ClinicaRepository repository, ClinicaInDtoToClinica mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Clinica crearClinica(ClinicaInDto clinicaInDto){
        Clinica clinica=mapper.map(clinicaInDto);
        return this.repository.save(clinica);
    }

    public List<Clinica> findAll(){
        return this.repository.findAll();
    }

    public Clinica findById(int id_clinica){
        return this.repository.findById(id_clinica);
    }
    @Transactional
    public void actualizarClinica(int id_clinica,String nombre, String direccion){
        this.repository.actualizarClinica(id_clinica,nombre,direccion);
    }
}

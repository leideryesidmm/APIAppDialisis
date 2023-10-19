package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.exceptions.ToDoExceptions;
import com.dialisis.dialisisperitoneal.mapper.ClinicaInDtoToClinica;
import com.dialisis.dialisisperitoneal.persistence.entity.Clinica;
import com.dialisis.dialisisperitoneal.persistence.repository.ClinicaRepository;
import com.dialisis.dialisisperitoneal.service.dto.ClinicaInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Clinica findById(int idClinica){
        Optional<Clinica> optionalClinica = this.repository.findById(idClinica);
        if (optionalClinica.isEmpty()) {
            throw new ToDoExceptions("Clinica no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalClinica.get();
    }
    @Transactional
    public void actualizarClinica(int idClinica,String nombre, String direccion){
        this.repository.actualizarClinica(idClinica,nombre,direccion);
    }
}

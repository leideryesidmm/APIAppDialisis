package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.ComorbilidadInDtoToComorbilidad;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Comorbilidad;
import com.Dialisis.DialisisPeritoneal.persistence.repository.ComorbilidadRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.ComorbilidadInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ComorbilidadService {

    private final ComorbilidadRepository repository;
    private final ComorbilidadInDtoToComorbilidad mapper;


    public ComorbilidadService(ComorbilidadRepository repository, ComorbilidadInDtoToComorbilidad mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Comorbilidad crearComorbilidad(ComorbilidadInDto comorbilidadInDto){
        Comorbilidad comorbilidad =mapper.map(comorbilidadInDto);
        return this.repository.save(comorbilidad);
    }

    public List<Comorbilidad> findAll(){
        return this.repository.findAll();
    }
    public Comorbilidad findById(int idComorbilidad){
        Optional<Comorbilidad> optionalComorbilidad = this.repository.findById(idComorbilidad);
        if (optionalComorbilidad.isEmpty()) {
            throw new ToDoExceptions("Comorbilidad no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalComorbilidad.get();
    }

    @Transactional
    public void actualizarCormobilidad(int idCormobilidad, long paciente, int enfermedad){
        this.repository.actualizarComorbilidad(idCormobilidad,paciente,enfermedad);
    }
    @Transactional
    public void inactivarCormobilidad(int idCormobilidad){
        this.repository.inactivarComorbilidad(idCormobilidad);
    }
    @Transactional
    public void activarCormobilidad(int idCormobilidad){
        this.repository.activarComorbilidad(idCormobilidad);
    }

    public List<Comorbilidad> findEnfermedadesPasadas(String paciente){
        return repository.findAllActivo(paciente);
    }
}

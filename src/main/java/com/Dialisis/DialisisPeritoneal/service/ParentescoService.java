package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.ParentescoInDtoToParentesco;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Parentesco;
import com.Dialisis.DialisisPeritoneal.persistence.repository.ParentescoRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.ParentescoInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ParentescoService {

    private final ParentescoRepository repository;
    private final ParentescoInDtoToParentesco mapper;

    public ParentescoService(ParentescoRepository repository, ParentescoInDtoToParentesco mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Parentesco crearParentesco(ParentescoInDto parentescoInDto){
        Parentesco parentesco= mapper.map(parentescoInDto);
        return this.repository.save(parentesco);
    }

    public List<Parentesco> findAll(){
        return this.repository.findAll();
    }

    public Parentesco findById(int idParentesco){
        Optional<Parentesco> optionalParentesco= this.repository.findById(idParentesco);
        if (optionalParentesco.isEmpty()) {
            throw new ToDoExceptions("Parentesco no encontrado", HttpStatus.NOT_FOUND);
        }
        return optionalParentesco.get();
    }
    @Transactional
    public void actualizarParentesco(int idParentesco, String descricion){
        this.repository.actualizarParentesco(idParentesco,descricion);
    }
}

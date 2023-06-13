package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ExceptionsEntitys;
import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.AlergiaInDtoToAlergia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Alergia;
import com.Dialisis.DialisisPeritoneal.persistence.repository.AlergiaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.AlergiaInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlergiaService {

    private final AlergiaRepository repository;
    private final AlergiaInDtoToAlergia mapper;

    private final ExceptionsEntitys exceptionsEntitysAlergia;

    public AlergiaService(AlergiaRepository repositorio, AlergiaInDtoToAlergia mapper, ExceptionsEntitys exceptionsEntitysAlergia) {
        this.repository = repositorio;
        this.mapper = mapper;
        this.exceptionsEntitysAlergia = exceptionsEntitysAlergia;
    }

    public Alergia crearAlergia(AlergiaInDto alergiaInDto){
            Alergia alergia = mapper.map(alergiaInDto);
            return this.repository.save(alergia);
    }

    public List<Alergia> findAll(){

        return this.repository.findAll();
    }

    public Alergia findById(int idAlergia){
        exceptionsEntitysAlergia.errorId(idAlergia);
        return this.repository.findById(idAlergia);
    }
    @Transactional
    public void actualizarAlergia(String nombre, int idAlergia){
        exceptionsEntitysAlergia.actualizarAlergia(idAlergia,nombre);
    //Optional<Alergia> optionalAlergia = this.repository.findById(idAlergia);
    //if (optionalAlergia.isEmpty()) {
        //throw new ToDoExceptions("Alergia no encontrada", HttpStatus.NOT_FOUND);
    //}
        this.repository.actualizarAlergia(nombre,idAlergia);
}
}

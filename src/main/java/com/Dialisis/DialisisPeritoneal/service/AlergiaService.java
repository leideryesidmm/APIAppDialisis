package com.Dialisis.DialisisPeritoneal.service;

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

    public AlergiaService(AlergiaRepository repositorio, AlergiaInDtoToAlergia mapper) {
        this.repository = repositorio;
        this.mapper = mapper;
    }

    public Alergia crearAlergia(AlergiaInDto alergiaInDto){
            Alergia alergia = mapper.map(alergiaInDto);
            return this.repository.save(alergia);
    }

    public List<Alergia> findAll(){

        return this.repository.findAll();
    }

    public Alergia findById(int id_alergia){
        Optional<Alergia> optionalTask = this.repository.findById(id_alergia);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Alergia no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalTask.get();
    }
@Transactional
    public void actualizarAlergia(String nombre, int id_alergia){
        this.repository.actualizarAlergia(nombre,id_alergia);
}
}

package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.exceptions.ExceptionsEntitys;
import com.dialisis.dialisisperitoneal.mapper.AlergiaInDtoToAlergia;
import com.dialisis.dialisisperitoneal.persistence.entity.Alergia;
import com.dialisis.dialisisperitoneal.persistence.repository.AlergiaRepository;
import com.dialisis.dialisisperitoneal.service.dto.AlergiaInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public Alergia actualizarAlergia(int idAlergia, AlergiaInDto alergiaInDto){
       Alergia alergia=mapper.map(alergiaInDto);
       alergia.setIdAlergia(idAlergia);
        return this.repository.save(alergia);
}
}

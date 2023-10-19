package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.RecambioHechoInDtoToRecambioHecho;
import com.dialisis.dialisisperitoneal.persistence.entity.Recambio;
import com.dialisis.dialisisperitoneal.persistence.entity.RecambioHecho;
import com.dialisis.dialisisperitoneal.persistence.repository.RecambioHechoRepository;
import com.dialisis.dialisisperitoneal.service.dto.RecambioHechoInDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecambioHechoService {

    private final RecambioHechoRepository repository;
    private final RecambioHechoInDtoToRecambioHecho mapper;

    public RecambioHechoService(RecambioHechoRepository repository, RecambioHechoInDtoToRecambioHecho mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RecambioHecho crearRecambio(RecambioHechoInDto recambioHechoInDto){
        RecambioHecho recambioHecho= mapper.map(recambioHechoInDto);
        return this.repository.save(recambioHecho);
    }
    public RecambioHecho editarRecambio(RecambioHechoInDto recambioHechoInDto, int idRecambioHecho){
        RecambioHecho recambioHecho1=this.findRecambioById(idRecambioHecho);
        RecambioHecho recambioHecho= mapper.map(recambioHechoInDto);
        recambioHecho.setIdRecambioHecho(idRecambioHecho);
        recambioHecho.setRecambio(recambioHecho1.getRecambio());
        return this.repository.save(recambioHecho);
    }
    public List<RecambioHecho> findByRecambio(Recambio recambio){
        return this.repository.findByRecambio(recambio);
    }

    public RecambioHecho findByRecambioAndFecha(int recambio, LocalDate fecha){
        return this.repository.findByRecambioAndFecha(recambio,fecha);
    }

    public RecambioHecho findRecambioById(int idRecambioHecho){
        return this.repository.findById(idRecambioHecho);
    }
}

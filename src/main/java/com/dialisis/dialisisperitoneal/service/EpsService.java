package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.EpsInDtoToEps;
import com.dialisis.dialisisperitoneal.persistence.entity.Eps;
import com.dialisis.dialisisperitoneal.persistence.repository.EpsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpsService {
    private final EpsRepository repository;
    private final EpsInDtoToEps mapper;

    public EpsService(EpsRepository repositorio, EpsInDtoToEps mapper) {
        this.repository = repositorio;
        this.mapper = mapper;
    }

    public List<Eps> findAllEps(){

        return this.repository.findAll();
    }
}

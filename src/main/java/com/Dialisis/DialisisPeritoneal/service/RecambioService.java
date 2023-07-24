package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.RecambioInDtoToRecambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.repository.RecambioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecambioService {

    private final RecambioRepository repository;
    private final RecambioInDtoToRecambio mapper;

    public RecambioService(RecambioRepository repository, RecambioInDtoToRecambio mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Recambio> findByPrescripcionDia(PrescripcionDia prescripcionDia){
        return this.repository.findByPrescripcionDia(prescripcionDia);
    }
}

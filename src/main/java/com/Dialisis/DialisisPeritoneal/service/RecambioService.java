package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.RecambioInDtoToRecambio;
import com.dialisis.dialisisperitoneal.persistence.entity.PrescripcionDia;
import com.dialisis.dialisisperitoneal.persistence.entity.Recambio;
import com.dialisis.dialisisperitoneal.persistence.repository.RecambioRepository;
import com.dialisis.dialisisperitoneal.service.dto.RecambioInDto;
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

    public Recambio crearRecambio(RecambioInDto recambioInDto, Recambio recambio){
        Recambio recamb= mapper.map(recambioInDto);
        recamb.setPrescripcionDia(recambio.getPrescripcionDia());
        return this.repository.save(recamb);
    }

    public List<Recambio> findByPrescripcionDia(PrescripcionDia prescripcionDia){
        return this.repository.findByPrescripcionDia(prescripcionDia);
    }

    public void deleteById(int idRecambio){
        this.repository.deleteById(idRecambio);

    }




}

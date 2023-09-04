package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.RecambioInDtoToRecambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.RecambioHecho;
import com.Dialisis.DialisisPeritoneal.persistence.repository.RecambioRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.RecambioInDto;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public void deleteById(int id_recambio){
        this.repository.deleteById(id_recambio);

    }




}

package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.RecambioInDtoToRecambio;
import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
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

    public List<Recambio> findByPrescripcionDia(PrescripcionDia prescripcionDia){
        return this.repository.findByPrescripcionDia(prescripcionDia);
    }

    public Recambio crearRecambio(RecambioInDto recambioInDto){
            Recambio recambio= mapper.map(recambioInDto);
       /* LocalDateTime fecha= LocalDateTime.now();
        recambio.setFecha(fecha);
        LocalDateTime hora= LocalDateTime.now();
        recambio.setHora(hora);*/
        return this.repository.save(recambio);
    }
}

package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.VisitaEspecialistaInDtoToVisitaEspecialista;
import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.persistence.repository.VisitaEspecialistaRepository;
import com.dialisis.dialisisperitoneal.service.dto.VisitaEspecialistaInDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitaEspecialistaService {

    private final VisitaEspecialistaRepository repository;
    private final VisitaEspecialistaInDtoToVisitaEspecialista mapper;

    public VisitaEspecialistaService(VisitaEspecialistaRepository repository, VisitaEspecialistaInDtoToVisitaEspecialista mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public VisitaEspecialista crearVisita(VisitaEspecialistaInDto visitaEspecialistaInDto) {
        VisitaEspecialista visitaEspecialista = mapper.map(visitaEspecialistaInDto);
        return this.repository.save(visitaEspecialista);
    }

    public VisitaEspecialista findUltimaVisita(int idCita) {
        List<VisitaEspecialista> visitas=this.repository.findUltimaVisita(idCita);
        if(visitas.isEmpty())
            return null;
        else
            return visitas.get(0);
    }

    public List<VisitaEspecialista> findAllVisitas(List<Cita> citas){
        List<VisitaEspecialista> visitas=new ArrayList<>();
        for (Cita cita:citas){
            VisitaEspecialista visitaEspecialista=this.repository.findByCita(cita);
            if(visitaEspecialista!=null)
                visitas.add(visitaEspecialista);
        }
            return visitas;
    }

    @Transactional
    public void actualizarVisita(int idVisita, VisitaEspecialistaInDto visitaEspecialistaInDto){
        VisitaEspecialista visitaEspecialista= mapper.map(visitaEspecialistaInDto);
        visitaEspecialista.setIdVistaEspecialista(idVisita);
        this.repository.save(visitaEspecialista);
    }
}


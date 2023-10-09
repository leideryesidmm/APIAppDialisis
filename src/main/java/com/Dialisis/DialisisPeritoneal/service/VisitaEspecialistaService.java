package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.RecambioHechoInDtoToRecambioHecho;
import com.Dialisis.DialisisPeritoneal.mapper.VisitaEspecialistaInDtoToVisitaEspecialista;
import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.persistence.repository.RecambioHechoRepository;
import com.Dialisis.DialisisPeritoneal.persistence.repository.VisitaEspecialistaRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.FormulaMedicamentoInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.RecambioHechoInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.VisitaEspecialistaInDto;
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
        System.out.println(idCita);
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
        if(visitas.isEmpty())
            return null;
            else{
                return visitas;}
    }

    @Transactional
    public void actualizarVisita(int idVisita, VisitaEspecialistaInDto visitaEspecialistaInDto){
        VisitaEspecialista visitaEspecialista= mapper.map(visitaEspecialistaInDto);
        visitaEspecialista.setIdVistaEspecialista(idVisita);
        System.out.println(visitaEspecialista);
        this.repository.save(visitaEspecialista);
    }
}


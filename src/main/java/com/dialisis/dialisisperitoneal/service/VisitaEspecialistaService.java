package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.VisitaEspecialistaInDtoToVisitaEspecialista;
import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.persistence.repository.VisitaEspecialistaRepository;
import com.dialisis.dialisisperitoneal.service.dto.VisitaEspecialistaInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitaEspecialistaService {

    private final VisitaEspecialistaRepository repository;
    private final VisitaEspecialistaInDtoToVisitaEspecialista mapper;
    private EncryptionService encryptionService;

    public VisitaEspecialistaService(VisitaEspecialistaRepository repository, VisitaEspecialistaInDtoToVisitaEspecialista mapper, EncryptionService encryptionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }

    public VisitaEspecialista crearVisita(VisitaEspecialistaInDto visitaEspecialistaInDto) {
        VisitaEspecialista visitaEspecialista = mapper.map(visitaEspecialistaInDto);
        visitaEspecialista= encryptionService.getEncFrontend().getVisitaEspecialistas().desencriptar(visitaEspecialista);
        visitaEspecialista=encryptionService.getEncBackend().getVisitaEspecialistas().encriptar(visitaEspecialista);
         this.repository.save(visitaEspecialista);
        visitaEspecialista=encryptionService.getEncBackend().getVisitaEspecialistas().desencriptar(visitaEspecialista);
        return encryptionService.getEncFrontend().getVisitaEspecialistas().desencriptar(visitaEspecialista);
    }

    public VisitaEspecialista findUltimaVisita(int idCita) {
        List<VisitaEspecialista> visitas = this.repository.findUltimaVisita(idCita);
        VisitaEspecialista visita;
        if(visitas.isEmpty())
            return null;
        else {
            visita = visitas.get(0);
            visita = encryptionService.getEncBackend().getVisitaEspecialistas().desencriptar(visita);
            visita = encryptionService.getEncFrontend().getVisitaEspecialistas().encriptar(visita);
            return visita;
        }
    }

    public List<VisitaEspecialista> findAllVisitas(List<Cita> citas) {
        List<VisitaEspecialista> visitas = new ArrayList<>();
        for (Cita cita : citas) {
            cita = encryptionService.getEncFrontend().getCita().desencriptar(cita);
            cita = encryptionService.getEncBackend().getCita().encriptar(cita);
            VisitaEspecialista visitaEspecialista = this.repository.findByCita(cita);
            visitaEspecialista = encryptionService.getEncBackend().getVisitaEspecialistas().desencriptar(visitaEspecialista);
            visitaEspecialista = encryptionService.getEncFrontend().getVisitaEspecialistas().encriptar(visitaEspecialista);
            visitas.add(visitaEspecialista);
        }
        return visitas;
    }

    @Transactional
    public void actualizarVisita(int idVisita, VisitaEspecialistaInDto visitaEspecialistaInDto){
        VisitaEspecialista visitaEspecialista= mapper.map(visitaEspecialistaInDto);
        visitaEspecialista.setIdVistaEspecialista(idVisita);
        visitaEspecialista=encryptionService.getEncFrontend().getVisitaEspecialistas().desencriptar(visitaEspecialista);
        visitaEspecialista=encryptionService.getEncBackend().getVisitaEspecialistas().encriptar(visitaEspecialista);
        this.repository.save(visitaEspecialista);
    }
}
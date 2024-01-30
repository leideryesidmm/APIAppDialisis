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
        VisitaEspecialista visita = this.repository.findUltimaVisita(idCita);
        if(visita==null)
            return null;
        else {
            Cita cita2=new Cita(visita.getCita());
            cita2=encryptionService.getEncBackend().getCita().desencriptar(cita2);
            cita2=encryptionService.getEncFrontend().getCita().encriptar(cita2);
            visita.setCita(cita2);
            return visita;
        }
    }



    public List<VisitaEspecialista> findAllVisitas(List<Cita> citas) {
        try {
            List<VisitaEspecialista> visitas = new ArrayList<>();
            for (Cita cita : citas) {
                cita = encryptionService.getEncFrontend().getCita().desencriptar(cita);
                cita = encryptionService.getEncBackend().getCita().encriptar(cita);
                VisitaEspecialista visitaEspecialista = this.repository.findByCita(cita);
                if (visitaEspecialista != null) {
                    Cita cita2=new Cita(visitaEspecialista.getCita());
                    cita2=encryptionService.getEncBackend().getCita().desencriptar(cita2);
                    cita2=encryptionService.getEncFrontend().getCita().encriptar(cita2);
                    visitaEspecialista.setCita(cita2);
                   visitas.add(visitaEspecialista);
                }
            }
            return visitas;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
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
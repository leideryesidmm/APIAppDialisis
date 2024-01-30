package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.MedicoInDtoToMedico;
import com.dialisis.dialisisperitoneal.persistence.entity.Medico;
import com.dialisis.dialisisperitoneal.persistence.entity.VisitaEspecialista;
import com.dialisis.dialisisperitoneal.persistence.repository.MedicoRepository;
import com.dialisis.dialisisperitoneal.service.dto.MedicoInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final MedicoInDtoToMedico mapper;
    private final EncryptionService encryptionService;

    private final VisitaEspecialistaService visitaEspecialistaService;

    public MedicoService(MedicoRepository medicoRepository, MedicoInDtoToMedico mapper, EncryptionService encryptionService, VisitaEspecialistaService visitaEspecialistaService) {
        this.medicoRepository = medicoRepository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
        this.visitaEspecialistaService = visitaEspecialistaService;
    }

    public List<Medico> findAll(){
        List<Medico> medicosBack=this.medicoRepository.findAll();
            List<Medico> medicosEncriptados = new ArrayList<>();
            for (Medico med :
                    medicosBack) {
                Medico medDesencriptado = encryptionService.getEncBackend().getMedico().desencriptar(med);
                medicosEncriptados.add(encryptionService.getEncFrontend().getMedico().encriptar(medDesencriptado));
            }
            return medicosEncriptados;
    }
    public Medico findByCedula(String cedula){
        Medico medico= new Medico(cedula);
        medico=encryptionService.getEncFrontend().getMedico().desencriptar(medico);
        medico=encryptionService.getEncBackend().getMedico().encriptar(medico);
        medico= this.medicoRepository.findAllByCedula(medico.getCedula());
        medico=encryptionService.getEncBackend().getMedico().desencriptar(medico);
        return encryptionService.getEncFrontend().getMedico().encriptar(medico);
    }

    public void desencriptarPaciente(){
        try{;
            VisitaEspecialista visita =visitaEspecialistaService.findUltimaVisita(315);
            visita=encryptionService.getEncBackend().getVisitaEspecialistas().desencriptar(visita);
            //paciente=encryptionService.getEncBackend().getPaciente().encriptar(paciente);
            }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Medico crearMedico(MedicoInDto medicoInDto) {
        Medico medico = mapper.map(medicoInDto);
        Medico medicoDesencriptado= this.encryptionService.getEncFrontend().getMedico().desencriptar(medico);
        Medico medEncriptado=this.encryptionService.getEncBackend().getMedico().encriptar(medicoDesencriptado);
        return this.medicoRepository.save(medEncriptado);
    }

    @Transactional
    public void actualizarDatosMedico(Medico medicoInDto) {
        medicoInDto=encryptionService.getEncFrontend().getMedico().desencriptar(medicoInDto);
        medicoInDto =encryptionService.getEncBackend().getMedico().encriptar(medicoInDto);
        this.medicoRepository.save(medicoInDto);
    }
}

package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.MedicoInDtoToMedico;
import com.dialisis.dialisisperitoneal.persistence.entity.Medico;
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

    public MedicoService(MedicoRepository medicoRepository, MedicoInDtoToMedico mapper, EncryptionService encryptionService) {
        this.medicoRepository = medicoRepository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
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
    public boolean existeMedico(String medicoF){
        Medico medico= new Medico(medicoF);
        Medico medicoDesencriptado=encryptionService.getEncFrontend().getMedico().encriptar(medico);
        if(findAll()!=null) {
            List<Medico> medicosB=findAll();
            for (Medico med :
                    medicosB) {
                Medico medicoBdDesencriptados = encryptionService.getEncBackend().getMedico().desencriptar(med);
                if(medicoDesencriptado.getCedula().equals(medicoBdDesencriptados))
                    return true;
            }
        }
        return false;
    }
    public Medico findByCedula(String cedula){
        Medico medico= new Medico(cedula);
        medico=encryptionService.getEncFrontend().getMedico().desencriptar(medico);
        medico=encryptionService.getEncBackend().getMedico().encriptar(medico);
        medico= this.medicoRepository.findAllByCedula(medico.getCedula());
        medico=encryptionService.getEncBackend().getMedico().desencriptar(medico);
        return encryptionService.getEncFrontend().getMedico().encriptar(medico);
    }
    public Medico crearMedico(MedicoInDto medicoInDto) {
        Medico medico = mapper.map(medicoInDto);
        Medico medicoDesencriptado= this.encryptionService.getEncFrontend().getMedico().desencriptar(medico);
        Medico medEncriptado=this.encryptionService.getEncBackend().getMedico().encriptar(medicoDesencriptado);
        return this.medicoRepository.save(medEncriptado);
    }

    @Transactional
    public void actualizarDatosMedico(MedicoInDto medicoInDto, Medico medico) {
        Medico med= mapper.map(medicoInDto);
        med.setCedula(medico.getCedula());
        med=encryptionService.getEncFrontend().getMedico().desencriptar(med);
        med=encryptionService.getEncBackend().getMedico().encriptar(med);
        this.medicoRepository.save(med);
    }


}

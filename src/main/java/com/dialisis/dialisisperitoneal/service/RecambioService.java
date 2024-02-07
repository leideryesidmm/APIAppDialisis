package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.RecambioInDtoToRecambio;
import com.dialisis.dialisisperitoneal.persistence.entity.Alergia;
import com.dialisis.dialisisperitoneal.persistence.entity.PrescripcionDia;
import com.dialisis.dialisisperitoneal.persistence.entity.Recambio;
import com.dialisis.dialisisperitoneal.persistence.repository.RecambioRepository;
import com.dialisis.dialisisperitoneal.service.dto.RecambioInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecambioService {

    private final RecambioRepository repository;
    private final RecambioInDtoToRecambio mapper;
    private final EncryptionService encryptionService;

    public RecambioService(RecambioRepository repository, RecambioInDtoToRecambio mapper, EncryptionService encryptionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }

    public Recambio crearRecambio(RecambioInDto recambioInDto, Recambio recambio){
        Recambio recamb= mapper.map(recambioInDto);
        recamb.setPrescripcionDia(recambio.getPrescripcionDia());
        recamb= encryptionService.getEncFrontend().getRecambio().desencriptar(recamb);
        recamb= encryptionService.getEncBackend().getRecambio().encriptar(recamb);
        this.repository.save(recamb);
        Recambio recambioPres= new Recambio(recamb.getIdRecambio());
        recambioPres.setPrescripcionDia(recamb.getPrescripcionDia());
        recambioPres.setConcentracion(recamb.getConcentracion());
        recambioPres.setIntervaloTiempo(recamb.getIntervaloTiempo());
        recambioPres=encryptionService.getEncBackend().getRecambio().desencriptar(recambioPres);
        recambioPres= encryptionService.getEncFrontend().getRecambio().encriptar(recambioPres);
        return recambioPres;
    }

    public List<Recambio> findByPrescripcionDia(PrescripcionDia prescripcionDia){
        PrescripcionDia prescripcionDia1=new PrescripcionDia(prescripcionDia);
        prescripcionDia1=encryptionService.getEncFrontend().getPrescripcionDia().desencriptar(prescripcionDia1);
        prescripcionDia1=encryptionService.getEncBackend().getPrescripcionDia().encriptar(prescripcionDia1);
        List<Recambio> rec=this.repository.findByPrescripcionDia(prescripcionDia1);
        for (int i = 0; i < rec.size();i++) {
                Recambio recambioDesencriptado = encryptionService.getEncBackend().getRecambio().desencriptar(rec.get(i));
                Recambio recambioEncriptado= encryptionService.getEncFrontend().getRecambio().encriptar(recambioDesencriptado);
                rec.set(i, recambioEncriptado);
            }
        return rec;
    }


    public List<Recambio> findRecambiosByPrescripcionDia(PrescripcionDia prescripcionDia){
        PrescripcionDia prescripcionDia1=new PrescripcionDia(prescripcionDia);
        prescripcionDia1=encryptionService.getEncFrontend().getPrescripcionDia().desencriptar(prescripcionDia1);
        prescripcionDia1=encryptionService.getEncBackend().getPrescripcionDia().encriptar(prescripcionDia1);
        List<Recambio> rec=this.repository.findByPrescripcionDia(prescripcionDia1);
        System.out.println("\u001B[33m"+rec+"\u001B[0m");
        return rec;
    }
    public Recambio encriptandoRecambio(Recambio recambio){
        Recambio recambioEncriptado=encryptionService.getEncFrontend().getRecambio().desencriptar(recambio);
        recambioEncriptado=encryptionService.getEncBackend().getRecambio().encriptar(recambioEncriptado);
        return recambioEncriptado;
    }

    public void deleteById(int idRecambio){
        this.repository.deleteById(idRecambio);

    }




}

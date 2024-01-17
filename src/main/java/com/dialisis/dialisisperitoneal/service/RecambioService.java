package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.RecambioInDtoToRecambio;
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
        recamb=encryptionService.getEncBackend().getRecambio().desencriptar(recamb);
        return encryptionService.getEncFrontend().getRecambio().encriptar(recamb);
    }

    public List<Recambio> findByPrescripcionDia(PrescripcionDia prescripcionDia){
        Recambio recamb=new Recambio();
        recamb.setPrescripcionDia(prescripcionDia);
        recamb=encryptionService.getEncFrontend().getRecambio().desencriptar(recamb);
        recamb=encryptionService.getEncBackend().getRecambio().encriptar(recamb);
        List<Recambio> rec=this.repository.findByPrescripcionDia(recamb.getPrescripcionDia());
            for (int i = 0; i < rec.size();i++) {
                recamb = encryptionService.getEncBackend().getRecambio().desencriptar(rec.get(i));
                rec.set(i, recamb);
            }
        return rec;
    }

    public void deleteById(int idRecambio){
        this.repository.deleteById(idRecambio);

    }




}

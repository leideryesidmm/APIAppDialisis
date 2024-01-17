package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.PrescripcionDiaToPrescripcionDia;
import com.dialisis.dialisisperitoneal.persistence.entity.Cita;
import com.dialisis.dialisisperitoneal.persistence.entity.PrescripcionDia;
import com.dialisis.dialisisperitoneal.persistence.repository.PrescripcionDiaRepository;
import com.dialisis.dialisisperitoneal.service.dto.PrescripcionDiaInDto;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrescripcionDiaService {

    private final PrescripcionDiaRepository repository;
    private final PrescripcionDiaToPrescripcionDia mapper;
    private EncryptionService encryptionService;

    public PrescripcionDiaService(PrescripcionDiaRepository repository, PrescripcionDiaToPrescripcionDia mapper, EncryptionService encryptionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.encryptionService = encryptionService;
    }
    public PrescripcionDia findById( int id){
        PrescripcionDia p = this.repository.findByIdPrescripcionDia(id);
        p=encryptionService.getEncBackend().getPrescripcionDia().desencriptar(p);
        p=encryptionService.getEncFrontend().getPrescripcionDia().encriptar(p);
        return p;
    }



    public PrescripcionDia crearPrescripcionDia(PrescripcionDiaInDto prescripcionDiaInDto){
        PrescripcionDia prescripcionDia= mapper.map(prescripcionDiaInDto);
        prescripcionDia=encryptionService.getEncFrontend().getPrescripcionDia().desencriptar(prescripcionDia);
        prescripcionDia=encryptionService.getEncBackend().getPrescripcionDia().encriptar(prescripcionDia);
        this.repository.save(prescripcionDia);
        prescripcionDia=encryptionService.getEncBackend().getPrescripcionDia().desencriptar(prescripcionDia);
        return encryptionService.getEncFrontend().getPrescripcionDia().encriptar(prescripcionDia);
    }

    public List<PrescripcionDia> findAllPrescripciones() {

        List<PrescripcionDia> pd=this.repository.findAll();
        for(int i=0;i<pd.size();i++) {
            PrescripcionDia presD=encryptionService.getEncBackend().getPrescripcionDia().desencriptar(pd.get(i));
            presD=encryptionService.getEncFrontend().getPrescripcionDia().encriptar(presD);
            pd.set(i, presD);
        }
        return pd;
    }

    public List<PrescripcionDia> findByCita(Cita cita) {
        List<PrescripcionDia> pd=this.repository.findByCita(cita);
            for(int i=0;i<pd.size();i++) {
                PrescripcionDia presD=encryptionService.getEncBackend().getPrescripcionDia().desencriptar(pd.get(i));
                presD=encryptionService.getEncFrontend().getPrescripcionDia().encriptar(presD);
                pd.set(i, presD);
            }
            return pd;
    }

    public void deleteById(int idPrescripcionDia){
            this.repository.deleteById(idPrescripcionDia);

    }

}

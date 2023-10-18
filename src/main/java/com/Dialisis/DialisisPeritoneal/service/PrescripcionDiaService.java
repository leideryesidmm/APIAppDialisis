package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.PrescripcionDiaToPrescripcionDia;
import com.dialisis.dialisisperitoneal.persistence.entity.Cita;
import com.dialisis.dialisisperitoneal.persistence.entity.PrescripcionDia;
import com.dialisis.dialisisperitoneal.persistence.repository.PrescripcionDiaRepository;
import com.dialisis.dialisisperitoneal.service.dto.PrescripcionDiaInDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrescripcionDiaService {

    private final PrescripcionDiaRepository repository;
    private final PrescripcionDiaToPrescripcionDia mapper;

    public PrescripcionDia findById( int id){
        return this.repository.findByIdPrescripcionDia(id);
    }

    public PrescripcionDiaService(PrescripcionDiaRepository repository, PrescripcionDiaToPrescripcionDia mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PrescripcionDia crearPrescripcionDia(PrescripcionDiaInDto prescripcionDiaInDto){
        PrescripcionDia prescripcionDia= mapper.map(prescripcionDiaInDto);
        return this.repository.save(prescripcionDia);
    }

    public List<PrescripcionDia> findAllPrescripciones() {
        return this.repository.findAll();
    }

    public List<PrescripcionDia> findByCita(Cita cita) {
        return this.repository.findByCita(cita);
    }

    public void deleteById(int idPrescripcionDia){
            this.repository.deleteById(idPrescripcionDia);

    }

}

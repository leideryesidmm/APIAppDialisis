package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.exceptions.ToDoExceptions;
import com.Dialisis.DialisisPeritoneal.mapper.MedicamentoInDtoToMedicamento;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Medicamento;
import com.Dialisis.DialisisPeritoneal.persistence.repository.MedicamentoRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicamentoInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private final MedicamentoRepository repository;
    private final MedicamentoInDtoToMedicamento mapper;

    public MedicamentoService(MedicamentoRepository repository, MedicamentoInDtoToMedicamento mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Medicamento crearMedicamento(MedicamentoInDto medicamentoInDto){
        Medicamento med = mapper.map(medicamentoInDto);
        return this.repository.save(med);
    }

    public List<Medicamento> encontrarMedicamentos(){

        return this.repository.findAll();
    }

    public Medicamento findById(int idMedicamento){
        Optional<Medicamento> optionalMedicamento= this.repository.findById(idMedicamento);
        if (optionalMedicamento.isEmpty()) {
            throw new ToDoExceptions("Medicamento no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalMedicamento.get();
    }
    @Transactional
    public void actualizarMedicamento (String nombre, int concentracion, int viaAdministracion, String descripcion, int idMedicamento){
        this.repository.actualizarMedicamento(nombre,concentracion,viaAdministracion,descripcion,idMedicamento);
    }
}

package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.exceptions.ToDoExceptions;
import com.dialisis.dialisisperitoneal.mapper.CitaInDtoToCita;
import com.dialisis.dialisisperitoneal.persistence.entity.Cita;
import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import com.dialisis.dialisisperitoneal.persistence.entity.PrescripcionDia;
import com.dialisis.dialisisperitoneal.persistence.repository.CitaRepository;
import com.dialisis.dialisisperitoneal.service.dto.CitaInDto;
import com.dialisis.dialisisperitoneal.service.dto.PacienteInDto;
import com.dialisis.dialisisperitoneal.service.dto.uniones.UnionCitaPrescripcionDias;
import com.dialisis.dialisisperitoneal.service.dto.uniones.UnionPrescripcionDiasRecambios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    private final CitaRepository repository;
    private final CitaInDtoToCita mapper;

    private final PrescripcionDiaService prescripcionDiaService;
    private final RecambioService recambioService;


    public CitaService(CitaRepository repository, CitaInDtoToCita mapper, PrescripcionDiaService prescripcionDiaService, RecambioService recambioService) {
        this.repository = repository;
        this.mapper = mapper;
        this.prescripcionDiaService = prescripcionDiaService;
        this.recambioService = recambioService;
    }

    public Cita crearCita(CitaInDto citaInDto) {
            Cita cita = mapper.map(citaInDto);

            return this.repository.save(cita);
    }

    public List<Cita> findAllByPaciente(PacienteInDto paciente){
        Paciente cedula=new Paciente();
        cedula.setCedula(paciente.getCedula());
        return this.repository.findAllByPaciente(cedula);
    }
    public Cita findById(int idCita){
        Optional<Cita> optionalCita = this.repository.findById(idCita);
        if (optionalCita.isEmpty()) {
            throw new ToDoExceptions("Cita no encontrada", HttpStatus.NOT_FOUND);
        }
        return optionalCita.get();
    }

    public void deleteById(int idCita){
        Optional<Cita> optionalCita = this.repository.findById(idCita);
        if (optionalCita.isEmpty()) {
            throw new ToDoExceptions("Cita no encontrada", HttpStatus.NOT_FOUND);
        }else {
            this.repository.deleteById(idCita);
        }

    }


    public List<Cita> findAllCitasAntiguasByPaciente(Paciente cedula){
        LocalDateTime hoy=LocalDateTime.now();
        return this.repository.findAllCitasAntiguasByPaciente(cedula,hoy);
    }
    public List<Cita> findAllCitas(){
        return this.repository.findAll();
    }
    public List<Cita> findAllCitasFuturasByPaciente(Paciente cedula){
        LocalDateTime hoy=LocalDateTime.now();
        return this.repository.findAllCitasFuturasByPaciente(cedula,hoy);
    }


    public Cita findUltimaCita(Paciente paciente) {
        List<Cita> citas=this.repository.findUltimaCita(paciente);
            if(citas.isEmpty())
                return null;
            else {
                return citas.get(0);
            }
    }
    @Transactional
    public void  finalizarById(int cita){
        this.repository.finalizar(LocalDateTime.now(),cita);
    }
    public UnionCitaPrescripcionDias getPrescripcionActual(Paciente paciente) {
        Cita cita = findUltimaCita(paciente);
        if (cita == null)
            return null;
        else {
            Paciente p = cita.getPaciente();
            p.setFoto(null);
            cita.setPaciente(p);
            UnionCitaPrescripcionDias citaPres = new UnionCitaPrescripcionDias();
            citaPres.setCita(cita);
            List<PrescripcionDia> prescripcionDias = this.prescripcionDiaService.findByCita(cita);
            List<UnionPrescripcionDiasRecambios> listPrescripcionDiasRecambios = new ArrayList<>();
            for (PrescripcionDia prescripcionDia : prescripcionDias) {
                UnionPrescripcionDiasRecambios prescripcionDiasRecambios = new UnionPrescripcionDiasRecambios();
                prescripcionDiasRecambios.setPrescripcionDia(prescripcionDia);
                prescripcionDiasRecambios.setRecambios(this.recambioService.findByPrescripcionDia(prescripcionDia));
                listPrescripcionDiasRecambios.add(prescripcionDiasRecambios);
            }
            citaPres.setUnionPrescripcionDiasRecambios(listPrescripcionDiasRecambios);
            return citaPres;
        }
    }

}

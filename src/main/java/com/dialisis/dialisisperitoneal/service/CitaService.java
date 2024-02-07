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
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.http.HttpStatus;
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
    private final EncryptionService encryptionService;


    public CitaService(CitaRepository repository, CitaInDtoToCita mapper, PrescripcionDiaService prescripcionDiaService, RecambioService recambioService, EncryptionService encryptionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.prescripcionDiaService = prescripcionDiaService;
        this.recambioService = recambioService;
        this.encryptionService = encryptionService;
    }

    public Cita crearCita(CitaInDto citaInDto) {
            Cita cita = mapper.map(citaInDto);
            cita=encryptionService.getEncFrontend().getCita().desencriptar(cita);
           cita=encryptionService.getEncBackend().getCita().encriptar(cita);
            cita=this.repository.save(cita);
            //pasarle de cita a citaMedica
            Cita citaMedica=encryptionService.getEncBackend().getCita().desencriptar(new Cita(cita));
        citaMedica=encryptionService.getEncFrontend().getCita().encriptar(citaMedica);
            return citaMedica;
    }

    public List<Cita> findAllByPaciente(PacienteInDto paciente){
        Paciente cedula=new Paciente();
        cedula.setCedula(paciente.getCedula());
        cedula=encryptionService.getEncFrontend().getPaciente().desencriptar(cedula);
        cedula=encryptionService.getEncBackend().getPaciente().encriptar(cedula);
        List<Cita> citasBack=this.repository.findAllByPaciente(cedula);
        List<Cita> citas=new ArrayList<>();
        for (Cita cita : citasBack) {
            cita=encryptionService.getEncBackend().getCita().desencriptar(cita);
            cita=encryptionService.getEncFrontend().getCita().encriptar(cita);
            citas.add(cita);
        }
        return citas;
    }
    public Cita findById(int idCita){
        Optional<Cita> optionalCita = this.repository.findById(idCita);
        if (optionalCita.isEmpty()) {
            throw new ToDoExceptions("Cita no encontrada", HttpStatus.NOT_FOUND);
        }else{
        Cita cita=optionalCita.get();
        cita=encryptionService.getEncBackend().getCita().desencriptar(cita);
        cita=encryptionService.getEncFrontend().getCita().encriptar(cita);
        return cita;
        }
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
        cedula=encryptionService.getEncFrontend().getPaciente().desencriptar(cedula);
        cedula=encryptionService.getEncBackend().getPaciente().encriptar(cedula);
        List<Cita> citas =this.repository.findAllCitasAntiguasByPaciente(cedula,hoy);
        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            cita = encryptionService.getEncBackend().getCita().desencriptar(cita);
            cita = encryptionService.getEncFrontend().getCita().encriptar(cita);
            citas.set(i, cita);
        }
        return citas;
    }
    public List<Cita> findAllCitas() {
        List<Cita> citasBack = this.repository.findAll();
        for (int i = 0; i < citasBack.size(); i++) {
            Cita cita = citasBack.get(i);
            cita = encryptionService.getEncBackend().getCita().desencriptar(cita);
            cita = encryptionService.getEncFrontend().getCita().encriptar(cita);
            citasBack.set(i, cita);
        }
        return citasBack;
    }
    public List<Cita> findAllCitasFuturasByPaciente(Paciente cedula){
        LocalDateTime hoy=LocalDateTime.now();
        cedula=encryptionService.getEncFrontend().getPaciente().desencriptar(cedula);
        cedula=encryptionService.getEncBackend().getPaciente().encriptar(cedula);
        List<Cita> citas =this.repository.findAllCitasFuturasByPaciente(cedula,hoy);
        for (int i = 0; i < citas.size(); i++) {
            Cita cita = citas.get(i);
            cita = encryptionService.getEncBackend().getCita().desencriptar(cita);
            cita = encryptionService.getEncFrontend().getCita().encriptar(cita);
            citas.set(i, cita);
        }
        return citas;

    }


    public Cita findUltimaCita(Paciente paciente) {
        paciente=encryptionService.getEncFrontend().getPaciente().desencriptar(paciente);
        paciente=encryptionService.getEncBackend().getPaciente().encriptar(paciente);
        Cita cita=this.repository.findUltimaCita(paciente);
            if(cita==null)
                return null;
            else {
                cita=encryptionService.getEncBackend().getCita().desencriptar(cita);
                cita=encryptionService.getEncFrontend().getCita().encriptar(cita);
                return cita;
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

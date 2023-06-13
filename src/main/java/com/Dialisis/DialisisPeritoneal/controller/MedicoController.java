package com.Dialisis.DialisisPeritoneal.controller;
import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.ClinicaInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoClinicaInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Medico")
public class MedicoController{
    private final MedicoService medicoService;
    private final MedicoClinicaService medicoClinicaService;
    private final ClinicaService clinicaService;
    private final PacienteService pacienteService;
    private final PrescripcionService prescripcionService;
    public MedicoController(MedicoService medicoService, MedicoClinicaService medicoClinicaService, ClinicaService clinicaService, PacienteService pacienteService, PrescripcionService prescripcionService) {
        this.medicoService = medicoService;
        this.medicoClinicaService = medicoClinicaService;
        this.clinicaService = clinicaService;
        this.pacienteService = pacienteService;
        this.prescripcionService=prescripcionService;
    }

    @GetMapping("/findAllPatients")
    public List<Paciente> findAllPacientes(){
        return this.pacienteService.findAll();
    }
    @PostMapping("/createOrUpdatePatients")
    public Medico crearoUpdateMedico(@RequestBody MedicoInDto medicoInDto){
        return this.medicoService.createoUpdateMedico(medicoInDto);
    }
    @GetMapping("/findAllDoctors")
    public List<Medico> findAllMedicos(){
        return this.medicoService.findAll();
    }
    @GetMapping("/findDoctorByCC/{cc}")
    public Usuario findMedico(@PathVariable("cedula") String cedula){
        return this.medicoService.findByCedula(cedula);
    }
    @PatchMapping("/clinic/updateDoctorClinic/{idDoctorClinic},{doctor},{clinic}")
    public ResponseEntity<Void> actualizarMedicoClinica(@PathVariable("idMedicoClinica")int idMedicoClinica,
                                                        @PathVariable("medico")String medico,
                                                        @PathVariable("clinica")int clinica,
                                                        @PathVariable("activa")boolean activa){
        this.medicoClinicaService.actualizarMedicoClinica(idMedicoClinica,medico,clinica, activa);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clinic/listClinicsByDoctor/{cc}")
        public List<MedicoClinica> listarClinicasPorMedico(@PathVariable("cedula")String cedula){
        return this.medicoClinicaService.findAllByMedico(cedula);
    }

    @PostMapping("/clinic/createClinic/{cc}")
    public void crearClinica(@PathVariable("cedula")String cedula, @RequestBody ClinicaInDto clinicaInDto){
        Clinica clinica= this.clinicaService.crearClinica(clinicaInDto);
        agregarClinicaByMedico(cedula,clinica.getIdClinica());
    }

    @PostMapping("/clinic/addClinicByDoctor/{cc},{idClinic}")
    public void agregarClinicaByMedico(@PathVariable("cedula")String cedula,@PathVariable("idClinica")int idClinica){
        MedicoClinicaInDto medicoClinicaInDto=new MedicoClinicaInDto();
        medicoClinicaInDto.setMedico(cedula);
        medicoClinicaInDto.setClinica(idClinica);
        this.medicoClinicaService.crearMedicoClinica(medicoClinicaInDto);
    }
    @GetMapping("/clinic/findByDoctor/{cc},{idClinic}")
    public MedicoClinica findClinicaPorMedico(@PathVariable("cedula")String cedula,@PathVariable("idClinica")int idClinica) {
        return this.medicoClinicaService.findClinicaPorMedico(cedula, idClinica);
    }

    @PatchMapping("/clinic/activate/{cc}{idClinic}")
    public void activarClinica(@PathVariable("cedula")String cedula,@PathVariable("idClinica")int idClinica) {
        this.medicoClinicaService.activarClinica(cedula,idClinica);
    }

    @PatchMapping("/clinic/inactivar/{cc}{idClinica}")
    public void inactivarClinica(@PathVariable("cedula")String cedula,@PathVariable("idClinica")int idClinica) {
        this.medicoClinicaService.inactivarClinica(cedula,idClinica);
    }

    @GetMapping("/clinic/listPast/{cc}")
    public List<MedicoClinica> findClinicasPasadas(@PathVariable("cedula")String cedula){
        return this.medicoClinicaService.findClinicasPasadas(cedula);
    }
    @PostMapping("/prescription/createPrescription/{appointment}")
    public Prescripcion crearPrescripcion(@PathVariable("idCita")int idCita, @RequestBody PrescripcionInDto prescripcionInDto){
        return this.prescripcionService.createPrescripcion(prescripcionInDto);
    }

    /*@GetMapping("/prescripcion/listprescripciones/{cita}")
    public List<Prescripcion> listarPrescripcionesPorPaciente(@PathVariable("idCita")int idCita){
        return this.prescripcionService.findAllByCita(idCita);
    }*/

}

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

    public MedicoController(PrescripcionService prescripcionService,  MedicoService medicoService, MedicoClinicaService medicoClinicaService, ClinicaService clinicaService, PacienteService pacienteService) {
        this.medicoService = medicoService;
        this.medicoClinicaService = medicoClinicaService;
        this.clinicaService = clinicaService;
        this.pacienteService = pacienteService;
        this.prescripcionService=prescripcionService;
    }

    @GetMapping("/findAllPacientes")
    public List<Paciente> findAllPacientes(){
        return this.pacienteService.findAll();
    }
    @PostMapping("/crearOActualizar")
    public Medico crearoUpdateMedico(@RequestBody MedicoInDto medicoInDto){
        return this.medicoService.createoUpdateMedico(medicoInDto);
    }
    @GetMapping("/findAll")
    public List<Medico> findAllMedicos(){
        return this.medicoService.findAll();
    }
    @GetMapping("/findByCedula/{cedula}")
    public Usuario findMedico(@PathVariable("cedula") String cedula){
        return this.medicoService.findByCedula(cedula);
    }
    @PatchMapping("/clinica/actualizarMedicoClinica/{id_medico_clinica},{medico},{clinica}")
    public ResponseEntity<Void> actualizarMedicoClinica(@PathVariable("id_medico_clinica")int id_medico_clinica,
                                                        @PathVariable("medico")String medico,
                                                        @PathVariable("clinica")int clinica,
                                                        @PathVariable("activa")boolean activa){
        this.medicoClinicaService.actualizarMedicoClinica(id_medico_clinica,medico,clinica, activa);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clinica/listClinicasByMedico/{cedula}")
        public List<MedicoClinica> listarClinicasPorMedico(@PathVariable("cedula")String cedula){
        return this.medicoClinicaService.findAllByMedico(cedula);
    }

    @PostMapping("/clinica/crearClinica/{cedula}")
    public void crearClinica(@PathVariable("cedula")String cedula, @RequestBody ClinicaInDto clinicaInDto){
        Clinica clinica= this.clinicaService.crearClinica(clinicaInDto);
        agregarClinicaByMedico(cedula,clinica.getIdClinica());
    }

    @PostMapping("/clinica/añadirClinicaByMedico/{cedula},{id_clinica}")
    public void agregarClinicaByMedico(@PathVariable("cedula")String cedula,@PathVariable("id_clinica")int id_clinica){
        MedicoClinicaInDto medicoClinicaInDto=new MedicoClinicaInDto();
        medicoClinicaInDto.setMedico(cedula);
        medicoClinicaInDto.setClinica(id_clinica);
        this.medicoClinicaService.crearMedicoClinica(medicoClinicaInDto);
    }
    @GetMapping("/clinica/findByMedico/{cedula},{id_clinica}")
    public MedicoClinica findClinicaPorMedico(@PathVariable("cedula")String cedula,@PathVariable("id_clinica")int id_clinica) {
        return this.medicoClinicaService.findClinicaPorMedico(cedula, id_clinica);
    }

    @PatchMapping("/clinica/activar/{cedula}{id_clinica}")
    public void activarClinica(@PathVariable("cedula")String cedula,@PathVariable("id_clinica")int id_clinica) {
        this.medicoClinicaService.activarClinica(cedula,id_clinica);
    }

    @PatchMapping("/clinica/Inactivar/{cedula}{id_clinica}")
    public void inactivarClinica(@PathVariable("cedula")String cedula,@PathVariable("id_clinica")int id_clinica) {
        this.medicoClinicaService.inactivarClinica(cedula,id_clinica);
    }

    @GetMapping("/clinica/listPasadas/{cedula}")
    public List<MedicoClinica> findClinicasPasadas(@PathVariable("cedula")String cedula){
        return this.medicoClinicaService.findClinicasPasadas(cedula);
    }
    @PostMapping("/prescripcion/crearPrescripcion/{cita}")
    public Prescripcion crearPrescripcion(@PathVariable("cita")int id_cita, @RequestBody PrescripcionInDto prescripcionInDto){
        return this.prescripcionService.createPrescripcion(prescripcionInDto);
    }

    @GetMapping("/findPrescripcionByCita/{idCita}")
    public Prescripcion findAllByCita(@PathVariable("idCita")int idCita){
        return this.prescripcionService.findAllByCita(idCita);
    }

    @PatchMapping("actualizarPrescripcion/{idCita}")
    public ResponseEntity<Void> actualizarPrescripcion(@PathVariable("orificioSalida")PrescripcionInDto orificioSalida,
                                                        @PathVariable("nocheSeca")boolean nocheSeca,
                                                        @PathVariable("id_prescripcion")int id_prescripcion){
        this.prescripcionService.actualizarPrescripcion(orificioSalida, nocheSeca, id_prescripcion);
        return ResponseEntity.noContent().build();
    }

    /*@GetMapping("/prescripcion/listprescripciones/{cita}")
    public List<Prescripcion> listarPrescripcionesPorPaciente(@PathVariable("idCita")int idCita){
        return this.prescripcionService.findAllByCita(idCita);
    }*/

}

package com.Dialisis.DialisisPeritoneal.controller;
import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.ClinicaInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.PrescripcionInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Medico")
public class MedicoController{
    private final MedicoService medicoService;
    private final ClinicaService clinicaService;
    private final PacienteService pacienteService;
    private final PrescripcionService prescripcionService;

    public MedicoController(PrescripcionService prescripcionService,  MedicoService medicoService,ClinicaService clinicaService, PacienteService pacienteService) {
        this.medicoService = medicoService;
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



    @PostMapping("/clinica/crearClinica/{cedula}")
    public void crearClinica(@PathVariable("cedula")String cedula, @RequestBody ClinicaInDto clinicaInDto){
        Clinica clinica= this.clinicaService.crearClinica(clinicaInDto);
    }


    @PostMapping("/prescripcion/crearPrescripcion/{cita}")
    public Prescripcion crearPrescripcion(@PathVariable("cita")int id_cita, @RequestBody PrescripcionInDto prescripcionInDto){
        return this.prescripcionService.createPrescripcion(prescripcionInDto);
    }

    /*@GetMapping("/findPrescripcionByCita/{idCita}")
    public Prescripcion findAllByCita(@PathVariable("idCita")int idCita){
        Cita cita =new Cita(idCita);
        return this.prescripcionService.findAllByCita(cita);
    }*/

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

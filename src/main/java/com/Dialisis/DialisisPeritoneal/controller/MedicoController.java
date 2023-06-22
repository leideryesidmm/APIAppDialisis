package com.Dialisis.DialisisPeritoneal.controller;
import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.*;
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
    private final CitaService citaService;

    public MedicoController(PrescripcionService prescripcionService, MedicoService medicoService, ClinicaService clinicaService, PacienteService pacienteService, CitaService citaService) {
        this.medicoService = medicoService;
        this.clinicaService = clinicaService;
        this.pacienteService = pacienteService;
        this.prescripcionService=prescripcionService;
        this.citaService = citaService;
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


    @PostMapping("/prescripcion/crearPrescripcion")
    public Prescripcion crearPrescripcion(@RequestBody PrescripcionCitaDto prescripcionCitaDto) {
        PrescripcionInDto prescripcionInDto = prescripcionCitaDto.getPrescripcion();
        CitaInDto citaInDto = prescripcionCitaDto.getCita();

        Prescripcion pre = prescripcionService.createPrescripcion(prescripcionInDto);
        citaInDto.setPrescripcion(pre.getIdPrescripcion());
        System.out.println(citaInDto);
        citaService.crearCita(citaInDto);

        return pre;
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
    @GetMapping("/prescripciones/listPrescripciones")
    public List<Cita> findAllPrescripciones(){

        return this.citaService.findAllCitas();
    }    /*@GetMapping("/prescripcion/listprescripciones/{cita}")
    public List<Prescripcion> listarPrescripcionesPorPaciente(@PathVariable("idCita")int idCita){
        return this.prescripcionService.findAllByCita(idCita);
    }*/

}

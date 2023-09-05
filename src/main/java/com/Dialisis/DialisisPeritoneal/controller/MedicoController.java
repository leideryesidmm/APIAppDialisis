package com.Dialisis.DialisisPeritoneal.controller;
import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Medico")
public class MedicoController{
    private final MedicoService medicoService;
    private final ClinicaService clinicaService;
    private final PacienteService pacienteService;
    private final PrescripcionDiaService prescripcionService;
    private final CitaService citaService;
    private final VisitaEspecialistaService visitaService;
    private  final ChequeoMensualService chequeoService;

    public MedicoController(PrescripcionDiaService prescripcionService, MedicoService medicoService, ClinicaService clinicaService, PacienteService pacienteService, CitaService citaService, VisitaEspecialistaService visitaService, ChequeoMensualService chequeoService) {
        this.medicoService = medicoService;
        this.clinicaService = clinicaService;
        this.pacienteService = pacienteService;
        this.prescripcionService=prescripcionService;
        this.citaService = citaService;
        this.visitaService = visitaService;
        this.chequeoService = chequeoService;
    }

    @GetMapping("/findAllPacientes")
    public ResponseEntity<List<Paciente>> findAllPacientes(){
        List<Paciente> pacientes= this.pacienteService.findAll();

        if(pacientes==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
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

        //Prescripcion pre = prescripcionService.createPrescripcion(prescripcionInDto);
        //citaInDto.setPrescripcion(pre.getIdPrescripcion());

        citaService.crearCita(citaInDto);

        return null;
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

    @PatchMapping("/inhabilitarPaciente")
    public void inhabilitarPaciente(@RequestBody PacienteInDto pacienteInDto) {
        System.out.println(pacienteInDto);
       this.pacienteService.inactivarPaciente(pacienteInDto.getCedula());
    }

    @PatchMapping("/reactivarPaciente")
    public void reactivarPaciente(@RequestBody PacienteInDto pacienteInDto) {
        System.out.println(pacienteInDto);
        this.pacienteService.activarPaciente(pacienteInDto.getCedula());
    }

    @GetMapping("/findPacientesActivos")
    public ResponseEntity<List<Paciente>> findPacientesActivos(){
        List<Paciente> pacientes= this.pacienteService.findPacientesActivos();
        if(pacientes==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping("/visitaEspecialista")
    public ResponseEntity<VisitaEspecialista>  crearVisitaEspecialista(@RequestBody VisitaEspecialistaInDto visitaEspecialistaDto){
        System.out.println(visitaEspecialistaDto);
        VisitaEspecialista visitaEspecialista=this.visitaService.crearVisita(visitaEspecialistaDto);
            return ResponseEntity.ok(visitaEspecialista);

    }

    @PostMapping("/chequeoMensual")
    public ResponseEntity<ChequeoMensual>  crearChequeoMensual(@RequestBody ChequeoMensualInDto chequeoMensualInDto){
        ChequeoMensual chequeoMensual=this.chequeoService.crearChequeo(chequeoMensualInDto);
        return ResponseEntity.ok(chequeoMensual);

    }


    }

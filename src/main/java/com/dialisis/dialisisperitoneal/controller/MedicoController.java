package com.dialisis.dialisisperitoneal.controller;
import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.service.*;
import com.dialisis.dialisisperitoneal.service.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Medico")
public class MedicoController{
    private final MedicoService medicoService;
    private final ClinicaService clinicaService;
    private final PacienteService pacienteService;
    private final UsuarioService usuarioService;
    private final VisitaEspecialistaService visitaService;
    private  final ChequeoMensualService chequeoService;
    private final  EspecialidadService especialidadService;
    public MedicoController(MedicoService medicoService, ClinicaService clinicaService, PacienteService pacienteService, UsuarioService usuarioService, VisitaEspecialistaService visitaService, ChequeoMensualService chequeoService, EspecialidadService especialidadService) {
        this.medicoService = medicoService;
        this.clinicaService = clinicaService;
        this.pacienteService = pacienteService;
        this.usuarioService = usuarioService;
        this.visitaService = visitaService;
        this.chequeoService = chequeoService;
        this.especialidadService = especialidadService;
    }
    @GetMapping("/findAllPacientes")
    public ResponseEntity<List<Paciente>> findAllPacientes(){
        List<Paciente> pacientes= this.pacienteService.findAll();
        if(pacientes==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Medico>> findAllMedicos(){
        List<Medico> medicos= this.medicoService.findAll();
        if(medicos==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicos);
    }

    @PostMapping("/findByCedula/{cedula}")
    public Usuario findMedico(@PathVariable("cedula") String cedula){
        
        return this.medicoService.findByCedula(cedula);
    }
    @PostMapping("/clinica/crearClinica/{cedula}")
    public void crearClinica(@PathVariable("cedula")String cedula, @RequestBody ClinicaInDto clinicaInDto){
        this.clinicaService.crearClinica(clinicaInDto);
    }
    @PatchMapping("/inhabilitarPaciente")
    public void inhabilitarPaciente(@RequestBody PacienteInDto pacienteInDto) {
        this.usuarioService.inactivarUsuario(pacienteInDto.getCedula());
    }
    @PatchMapping("/reactivarPaciente")
    public void reactivarPaciente(@RequestBody PacienteInDto pacienteInDto) {
        this.usuarioService.activarUsuario(pacienteInDto.getCedula());
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
        VisitaEspecialista visitaEspecialista=this.visitaService.crearVisita(visitaEspecialistaDto);
        return ResponseEntity.ok(visitaEspecialista);
    }
    @PostMapping("/chequeoMensual")
    public ResponseEntity<ChequeoMensual>  crearChequeoMensual(@RequestBody ChequeoMensualInDto chequeoMensualInDto){
        ChequeoMensual chequeoMensual=this.chequeoService.crearChequeo(chequeoMensualInDto);
        return ResponseEntity.ok(chequeoMensual);
    }
    @PostMapping("/encontrarChequeo/{id_cita}")
    public ResponseEntity<ChequeoMensual> encontrarChequeo(@PathVariable("id_cita") int idCita) {
        ChequeoMensual chequeo=this.chequeoService.findUltimoChequeo(idCita);
        if(chequeo==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(chequeo);
    }
    @PostMapping("/encontrarVisita/{id_cita}")
    public ResponseEntity<VisitaEspecialista> encontrarVisita(@PathVariable("id_cita") int idCita) {
        VisitaEspecialista visita=this.visitaService.findUltimaVisita(idCita);
        if(visita==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(visita);
    }

    @GetMapping("DescEnc")
    public void descEnc(){
        this.medicoService.desencriptarPaciente();
    }
    @GetMapping("/findAllEspecialidad")
    public ResponseEntity<List<Especialidad>> findAllEspecialidad(){
        List<Especialidad> especialidades=this.especialidadService.findAllEspecialidad();
        if(especialidades==null||especialidades.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(especialidades);
        }
    }
}

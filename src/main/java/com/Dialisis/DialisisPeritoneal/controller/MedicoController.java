package com.Dialisis.DialisisPeritoneal.controller;
import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.ClinicaService;
import com.Dialisis.DialisisPeritoneal.service.MedicoClinicaService;
import com.Dialisis.DialisisPeritoneal.service.MedicoService;
import com.Dialisis.DialisisPeritoneal.service.PacienteService;
import com.Dialisis.DialisisPeritoneal.service.dto.ClinicaInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoClinicaInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.MedicoInDto;
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
    public MedicoController(MedicoService medicoService, MedicoClinicaService medicoClinicaService, ClinicaService clinicaService, PacienteService pacienteService) {
        this.medicoService = medicoService;
        this.medicoClinicaService = medicoClinicaService;
        this.clinicaService = clinicaService;
        this.pacienteService = pacienteService;
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
    public Usuario findMedico(@PathVariable("cedula") long cedula){
        return this.medicoService.findByCedula(cedula);
    }
    @PatchMapping("/clinica/actualizarMedicoClinica/{id_medico_clinica},{medico},{clinica}")
    public ResponseEntity<Void> actualizarMedicoClinica(@PathVariable("id_medico_clinica")int id_medico_clinica,
                                                        @PathVariable("medico")long medico,
                                                        @PathVariable("clinica")int clinica,
                                                        @PathVariable("activa")boolean activa){
        this.medicoClinicaService.actualizarMedicoClinica(id_medico_clinica,medico,clinica, activa);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clinica/listClinicasByMedico/{cedula}")
    public List<MedicoClinica> listarClinicasPorMedico(long cedula){
        return this.medicoClinicaService.findAllByMedico(cedula);
    }

    @PostMapping("/clinica/crearClinica/{cedula}, {id_clinica}")
    public void crearClinica(long cedula, @RequestBody ClinicaInDto clinicaInDto){
        Clinica clinica= this.clinicaService.crearClinica(clinicaInDto);
        agregarClinicaByMedico(cedula,clinica.getId_clinica());
    }

    @PostMapping("/clinica/añadirClinicaByMedico/{cedula},{id_clinica}")
    public void agregarClinicaByMedico(long cedula, int id_clinica){
        MedicoClinicaInDto medicoClinicaInDto=new MedicoClinicaInDto();
        medicoClinicaInDto.setMedico(cedula);
        medicoClinicaInDto.setClinica(id_clinica);
        this.medicoClinicaService.crearMedicoClinica(medicoClinicaInDto);
    }
    @GetMapping("/clinica/findByMedico")
    public MedicoClinica findClinicaPorMedico(long cedula,int id_clinica) {
        return this.medicoClinicaService.findClinicaPorMedico(cedula, id_clinica);
    }

    @PatchMapping("/clinica/activar/{cedula}{id_clinica}")
    public void activarClinica(long cedula, int id_clinica) {
        this.medicoClinicaService.activarClinica(cedula,id_clinica);
    }

    @PatchMapping("/clinica/Inactivar/{cedula}{id_clinica}")
    public void inactivarClinica(long cedula, int id_clinica) {
        this.medicoClinicaService.inactivarClinica(cedula,id_clinica);
    }

    @GetMapping("/clinica/listPasadas/{cedula}")
    public List<MedicoClinica> findClinicasPasadas(long cedula){
        return this.medicoClinicaService.findClinicasPasadas(cedula);
    }
}

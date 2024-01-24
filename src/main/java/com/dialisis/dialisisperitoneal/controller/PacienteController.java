package com.dialisis.dialisisperitoneal.controller;

import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.service.*;
import com.dialisis.dialisisperitoneal.service.dto.*;
import com.dialisis.dialisisperitoneal.service.dto.uniones.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final PacienteService pacienteService;
    private final ParentescoService parentescoService;
    private final CuidadorPacienteService cuidadorPacienteService;
    private final CuidadorService cuidadorService;
    public PacienteController(PacienteService pacienteService, ParentescoService parentescoService, CuidadorPacienteService cuidadorPacienteService, CuidadorService cuidadorService) {
        this.pacienteService = pacienteService;
        this.parentescoService = parentescoService;
        this.cuidadorPacienteService = cuidadorPacienteService;
        this.cuidadorService = cuidadorService;
    }
    @PostMapping("/crearPaciente")
    public Paciente crearPaciente(@RequestBody PacienteInDto pacienteInDto){
        return this.pacienteService.crearPaciente(pacienteInDto);
    }
    @PostMapping("/findPacienteByCedula")
    public ResponseEntity<Paciente> findPacienteByCedula(@RequestBody Paciente paciente){
        try {
            Paciente pac=this.pacienteService.findByCedula(paciente);
            if(pac!=null){
                return ResponseEntity.ok(pac);}
            else{
                return ResponseEntity.status(204).build();
            }
        }
        catch (Exception e) {
        e.getMessage();
        return ResponseEntity.noContent().build();
        }
    }
    @PatchMapping("/actualizar")
    public ResponseEntity<Void> actualizarDatosPaciente(@RequestBody Paciente paciente){
        try{
        this.pacienteService.actualizarDatosPaciente(paciente);
        return ResponseEntity.ok().build();}
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }
    @PostMapping("/cuidador/listCuidadorPacienteByPaciente")
    public ResponseEntity<List<CuidadorPaciente>> listarCuidadoresPorPaciente(@RequestBody PacienteInDto pacienteInDto){
        List<CuidadorPaciente> cuidadorPaciente = this.cuidadorPacienteService.findAllByPaciente(pacienteInDto.getCedula());
        if (cuidadorPaciente == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuidadorPaciente);
    }
    @PostMapping("/cuidador/findCuidadorActivo")
    public ResponseEntity<CuidadorPaciente> findCuidadorActivo(@RequestBody PacienteInDto pacienteInDto) {
        CuidadorPaciente cuidadorPaciente = this.cuidadorPacienteService.findCuidadorActivo(pacienteInDto.getCedula());
        if (cuidadorPaciente == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuidadorPaciente);
    }
    @PatchMapping("/cuidador/reactivarCuidador")
    public void reactivarCuidadorAntiguo(@RequestBody UnionCuidadorPacienteInDto unionCuidadorPacienteInDto) {
        CuidadorPaciente cuidadorPaciente = this.cuidadorPacienteService.findCuidadorActivo(unionCuidadorPacienteInDto.getPacienteInDto().getCedula());
        if (cuidadorPaciente != null) {
            this.cuidadorPacienteService.inactivarCuidador(cuidadorPaciente.getIdCuidadorPaciente());
        }
        CuidadorPacienteInDto cuidadorPacienteInDto = new CuidadorPacienteInDto();
        cuidadorPacienteInDto.setCuidador(unionCuidadorPacienteInDto.getCuidadorInDto().getCedulaCuidador());
        cuidadorPacienteInDto.setPaciente(unionCuidadorPacienteInDto.getPacienteInDto().getCedula());
        LocalDate fechaIni = LocalDate.now();
        cuidadorPacienteInDto.setFechaIni(fechaIni);
        this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorPacienteInDto);
    }
    @PatchMapping("/cuidador/ReactivarCuidadorAntiguoSinActivo")
    public void reactivarCuidadorAntiguoSinActivo(@RequestBody CuidadorInDto cuidador) {
        CuidadorPacienteInDto cuidadorPacienteInDto = new CuidadorPacienteInDto();
        cuidadorPacienteInDto.setCuidador(cuidador.getCedulaCuidador());
        cuidadorPacienteInDto.setPaciente(cuidadorPacienteInDto.getCuidador());
        LocalDate fechaIni = LocalDate.now();
        cuidadorPacienteInDto.setFechaIni(fechaIni);
        this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorPacienteInDto);
    }
    @PostMapping("/cuidador/crear")
    public void crearCuidador(@RequestBody UnionCuidadorPacienteInDto unionCuidadorPacienteInDto) {
        CuidadorPaciente cuidadorPaciente = this.cuidadorPacienteService.findCuidadorActivo(unionCuidadorPacienteInDto.getPacienteInDto().getCedula());
        if (cuidadorPaciente != null) {
            this.cuidadorPacienteService.inactivarCuidador(cuidadorPaciente.getIdCuidadorPaciente());
        }
        Cuidador cuidador = this.cuidadorService.crearoActualizarCuidador(unionCuidadorPacienteInDto.getCuidadorInDto());
        CuidadorPacienteInDto cuidadorPacienteInDto = new CuidadorPacienteInDto();
        cuidadorPacienteInDto.setCuidador(cuidador.getCedulaCuidador());
        cuidadorPacienteInDto.setPaciente(unionCuidadorPacienteInDto.getPacienteInDto().getCedula());
        LocalDate fechaIni = LocalDate.now();
        cuidadorPacienteInDto.setFechaIni(fechaIni);
        this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorPacienteInDto);
    }
    @PatchMapping("/cuidador/inhabilitarCuidadorActivo")
    public void inhabilitarCuidadorActivo(@RequestBody UnionCuidadorPacienteInDto unionCuidadorPacienteInDto) {
        CuidadorPaciente cuidadorPaciente = this.cuidadorPacienteService.findCuidadorActivo(unionCuidadorPacienteInDto.getPacienteInDto().getCedula());
        if (cuidadorPaciente != null) {
            this.cuidadorPacienteService.inactivarCuidador(cuidadorPaciente.getIdCuidadorPaciente());
            cuidadorPaciente.setActivo(false);
        }
    }
    @PatchMapping("/cuidador/actualizar")
    public void actualizarCuidador(@RequestBody CuidadorInDto cuidadorInDto) {
        this.cuidadorService.actualizarCuidador(cuidadorInDto.getCedulaCuidador(), cuidadorInDto);
    }
    @GetMapping("/ListParentesco")
    public List<Parentesco> findAllParentesco() {
        return this.parentescoService.findAll();
    }
    @GetMapping("/findAllPacientesDatos")
    public List<Object[]> findAllPacientesDatos() {
        return this.pacienteService.findAllPacientesDatos();
    }
}
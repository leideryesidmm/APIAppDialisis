package com.dialisis.dialisisperitoneal.controller;

import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.service.*;
import com.dialisis.dialisisperitoneal.service.dto.*;
import com.dialisis.dialisisperitoneal.service.dto.uniones.UnionCitaPrescripcionDias;
import com.dialisis.dialisisperitoneal.service.dto.uniones.UnionPrescripcionDiasRecambios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Prescripcion")
public class PrescripcionController {
    private final CitaService citaService;
    private final PrescripcionDiaService prescripcionDiaService;
    private final RecambioService recambioService;
    private final VisitaEspecialistaService visitaEspecialistaService;
    private final ChequeoMensualService chequeoMensualService;


    public PrescripcionController(CitaService citaService, PrescripcionDiaService prescripcionDiaService, RecambioService recambioService, VisitaEspecialistaService visitaEspecialistaService, ChequeoMensualService chequeoMensualService) {
        this.citaService = citaService;
        this.prescripcionDiaService = prescripcionDiaService;
        this.recambioService = recambioService;
        this.visitaEspecialistaService = visitaEspecialistaService;
        this.chequeoMensualService = chequeoMensualService;
    }
    @PostMapping("/Cita")
    public Cita crearCita(@RequestBody CitaInDto citaInDto) {
        return this.citaService.crearCita(citaInDto);
    }
    @DeleteMapping("/EliminarCita/{id_cita}")
    public ResponseEntity<Void> deleteCita(@PathVariable("id_cita") int idCita) {
        this.citaService.deleteById(idCita);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/cita/eliminar/{id_cita}")
    public void eliminarCita(@PathVariable("id_cita")int idCita){
        this.citaService.deleteById(idCita);
    }
    @GetMapping("/prescripciones/listPrescripciones")
    public List<Cita> findAllPrescripciones(){
        return this.citaService.findAllCitas();
    }
    @GetMapping("/cita/listByPaciente/{cedula}")
    public List<Cita> findAllCitas(@PathVariable("cedula")String cedula){
        PacienteInDto paciente=new PacienteInDto();
        paciente.setCedula(cedula);
        return this.citaService.findAllByPaciente(paciente);
    }
    @GetMapping("/cita/antiguas/{cedula}")
    public List<Cita> findAllCitasAntiguasByPaciente(@PathVariable("cedula")String cedula){
        Paciente paciente=new Paciente(cedula);
        return this.citaService.findAllCitasAntiguasByPaciente(paciente);
    }
    @GetMapping("/cita/futuras")
    public List<Cita> findAllCitasFuturasByPaciente(@PathVariable("cedula")String cedula){
        Paciente paciente=new Paciente(cedula);
        return this.citaService.findAllCitasFuturasByPaciente(paciente);
    }
    @GetMapping("/cita/filtroPyF/{cedula},{filtro}")
    public List<Cita> findAllCitasPyFByPaciente(@PathVariable("cedula")String cedula,@PathVariable("filtro")byte filtro){
        Paciente paciente=new Paciente(cedula);
        if(filtro==1)
            return this.citaService.findAllCitasFuturasByPaciente(paciente);
        else return this.citaService.findAllCitasAntiguasByPaciente(paciente);
    }
    @GetMapping("/prescripcion")
    public ResponseEntity<List<Cita>> getPrescisciones(){
        List<Cita> citas=this.citaService.findAllCitas();
        if(citas==null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(citas);
    }
    @PostMapping("/prescripcionesByPaciente")
    public ResponseEntity<List<UnionCitaPrescripcionDias>> getPresciscionesByPaciente(@RequestBody PacienteInDto paciente){
        List<Cita> citas=this.citaService.findAllByPaciente(paciente);

        List<UnionCitaPrescripcionDias> prescripciones=new ArrayList<>();
        if(citas==null)
            return ResponseEntity.noContent().build();
        else {
            for (Cita cita:citas) {
                Paciente p=cita.getPaciente();
                p.setFoto(null);
                cita.setPaciente(p);
                UnionCitaPrescripcionDias prescripcion=new UnionCitaPrescripcionDias();
                prescripcion.setCita(cita);
                List<PrescripcionDia> prescripcionDias=this.prescripcionDiaService.findByCita(cita);
                List<UnionPrescripcionDiasRecambios> recambios=new ArrayList<>();
                for (PrescripcionDia prescripcionDia:prescripcionDias) {
                    UnionPrescripcionDiasRecambios precrip=new UnionPrescripcionDiasRecambios();
                    precrip.setPrescripcionDia(prescripcionDia);
                    precrip.setRecambios(this.recambioService.findByPrescripcionDia(prescripcionDia));
                    recambios.add(precrip);
                }
                prescripcion.setUnionPrescripcionDiasRecambios(recambios);
                prescripciones.add(prescripcion);
            }
            return ResponseEntity.ok(prescripciones);
        }
    }
    @PostMapping("/prescripcion/prescripcionActual")
    public ResponseEntity<UnionCitaPrescripcionDias> getPresciscionActual(@RequestBody PacienteInDto paciente){
        Paciente paciente1=new Paciente(paciente.getCedula());
        UnionCitaPrescripcionDias cita=this.citaService.getPrescripcionActual(paciente1);
        if(cita==null)
            return ResponseEntity.noContent().build();
        else{
            return ResponseEntity.ok(cita);
        }
    }
    @PostMapping("/ultimaCita")
    public ResponseEntity<Cita> ultimaCita(@RequestBody PacienteInDto paciente){
        Paciente paciente1=new Paciente(paciente.getCedula());
        Cita cita=this.citaService.findUltimaCita(paciente1);
        if(cita!=null)
            return ResponseEntity.ok(cita);
        else{
            return ResponseEntity.noContent().build();
        }
    }
    @PatchMapping("/finalizarPrescripcion/{idCita}")
    public ResponseEntity<Void>  finalizarPrescripcion(@PathVariable("idCita") int idCita) {
        try {
            this.citaService.finalizarById(idCita);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/prescripcionDia/eliminar/{id_prescripcionDia}")
    public void eliminarPrescripcionDia(@PathVariable("id_prescripcionDia")int idPrescripcionDia){
        this.prescripcionDiaService.deleteById(idPrescripcionDia);
    }
    @PostMapping("crear/prescripcionDia")
    public PrescripcionDia crearPrescripcionDia(@RequestBody PrescripcionDiaInDto prescripcionDiaInDto){
        return this.prescripcionDiaService.crearPrescripcionDia(prescripcionDiaInDto);
    }
    @PostMapping("/prescripcionDia/findByCita/{cita}")
    public List<PrescripcionDia> findprescripcionByCita(@PathVariable int cita){
        return this.prescripcionDiaService.findByCita(new Cita(cita));
    }
    @PostMapping("/prescripcion/visitas")
    public ResponseEntity<List<VisitaEspecialista>> findAllVisitas(@RequestBody PacienteInDto pacienteInDto) {
        List<Cita> citas= citaService.findAllByPaciente(pacienteInDto);
        List<VisitaEspecialista> visitas = visitaEspecialistaService.findAllVisitas(citas);
        if(visitas.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(visitas);
    }
    @PostMapping("/prescripcion/chequeos")
    public ResponseEntity<List<ChequeoMensual>> findAllChequeos(@RequestBody PacienteInDto pacienteInDto) {
        List<Cita> citas= citaService.findAllByPaciente(pacienteInDto);
        List<ChequeoMensual> chequeos = chequeoMensualService.findAllChequeos(citas);
        if(chequeos.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(chequeos);
    }
    @PatchMapping("ActualizarVisita/{idVisita}")
    public ResponseEntity<Void> actualizarVisita(@PathVariable("idVisita")int idVisita,
                                                 @RequestBody VisitaEspecialistaInDto visitaEspecialistaInDto){
        this.visitaEspecialistaService.actualizarVisita(idVisita,visitaEspecialistaInDto);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("ActualizarChequeo/{id_chequeo_mensual}")
    public ResponseEntity<Void> actualizarChequeo(@PathVariable("id_chequeo_mensual")int idChequeoMensual,
                                                  @RequestBody ChequeoMensualInDto chequeoMensualInDto){
        this.chequeoMensualService.actualizarChequeo(idChequeoMensual,chequeoMensualInDto);
        return ResponseEntity.noContent().build();
    }
}
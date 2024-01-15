package com.dialisis.dialisisperitoneal.controller;

import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.service.CitaService;
import com.dialisis.dialisisperitoneal.service.PrescripcionDiaService;
import com.dialisis.dialisisperitoneal.service.RecambioHechoService;
import com.dialisis.dialisisperitoneal.service.RecambioService;
import com.dialisis.dialisisperitoneal.service.dto.PacienteInDto;
import com.dialisis.dialisisperitoneal.service.dto.RecambioHechoInDto;
import com.dialisis.dialisisperitoneal.service.dto.RecambioInDto;
import com.dialisis.dialisisperitoneal.service.dto.uniones.UnionCitaPrescripcionDias;
import com.dialisis.dialisisperitoneal.service.dto.uniones.UnionPrescripcionDiasRecambios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Recambio")
public class RecambioController {
    private final RecambioService recambioService;
    private final RecambioHechoService recambioHechoService;
    private final PrescripcionDiaService prescripcionDiaService;
    private final CitaService citaService;

    public RecambioController(RecambioService recambioService, RecambioHechoService recambioHechoService, PrescripcionDiaService prescripcionDiaService, CitaService citaService) {
        this.recambioService = recambioService;
        this.recambioHechoService = recambioHechoService;
        this.prescripcionDiaService = prescripcionDiaService;
        this.citaService = citaService;
    }
    @DeleteMapping("/recambio/eliminar/{id_recambio}")
    public void eliminarRecambio(@PathVariable("id_recambio")int idRecambio){
        this.recambioService.deleteById(idRecambio);
    }
    @PostMapping("/recambio/findRecambioHechoByPaciente")
    public ResponseEntity<List<RecambioHecho>> findRecambioHechoByPaciente(@RequestBody PacienteInDto paciente){
        try{
            List<RecambioHecho> recambioHechos=new ArrayList<>();
            Paciente p=new Paciente(paciente.getCedula());
            UnionCitaPrescripcionDias presciscionActual=this.citaService.getPrescripcionActual(p);
            if(presciscionActual==null)
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            else{
                for(UnionPrescripcionDiasRecambios prescripcionDia :presciscionActual.getUnionPrescripcionDiasRecambios()) {
                    for(Recambio recambio:prescripcionDia.getRecambios()){
                        List<RecambioHecho> recambioHechoLista2=this.recambioHechoService.findByRecambio(recambio);
                        recambioHechos.addAll(recambioHechoLista2);
                    }
                }
            }
            return ResponseEntity.ok(recambioHechos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/prescripcion/crearRecambio")
    public void crearRecambio(@RequestBody RecambioInDto recambioInDto){
        Recambio recambio=new Recambio();
        recambio.setPrescripcionDia(this.prescripcionDiaService.findById(recambioInDto.getPrescripcionDia()));
        this.recambioService.crearRecambio(recambioInDto, recambio);
    }
    @PostMapping("/recambio/findRecambioByPrescripcion/{prescripcionDia}")
    public List<Recambio> findRecambiosByPrescripcion(@PathVariable int prescripcionDia){
        return  this.recambioService.findByPrescripcionDia(new PrescripcionDia(prescripcionDia));
    }
    @GetMapping("/findAllRecambiosHechos/{idCita}")
    public ResponseEntity<List<RecambioHecho>> findAllReacambioHechoByCita(@PathVariable("idCita") int idCita) {
        try {
            List<RecambioHecho> recambioHechos=new ArrayList<>();
            List<Recambio> recambioPrescri;
            Cita cita = new Cita(idCita);
            List<PrescripcionDia> prescripcionDias = this.prescripcionDiaService.findByCita(cita);
            for (PrescripcionDia prescripcionDia : prescripcionDias) {
                UnionPrescripcionDiasRecambios prescripcionDiasRecambios = new UnionPrescripcionDiasRecambios();
                prescripcionDiasRecambios.setPrescripcionDia(prescripcionDia);
                prescripcionDiasRecambios.setRecambios(this.recambioService.findByPrescripcionDia(prescripcionDia));
                recambioPrescri=prescripcionDiasRecambios.getRecambios();
                for (Recambio recambio:recambioPrescri) {
                    recambioHechos.addAll(this.recambioHechoService.findByRecambio(recambio));
                }
            }
            return ResponseEntity.ok(recambioHechos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/recambio/crearRecambioHecho")
    public ResponseEntity<RecambioHecho>  crearRecambioHecho(@RequestBody RecambioHechoInDto recambioHechoInDto){
        try{
            System.out.println(recambioHechoInDto);
            RecambioHecho recambioHecho=this.recambioHechoService.crearRecambio(recambioHechoInDto);
            return ResponseEntity.ok(recambioHecho);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/recambio/editarRecambioHecho/{id_recambioHecho}")
    public ResponseEntity<RecambioHecho>  editarRecambioHecho(@RequestBody RecambioHechoInDto recambioHechoInDto,@PathVariable("id_recambioHecho") int idRecambioHecho){
        try{
            RecambioHecho recambioHecho=this.recambioHechoService.editarRecambio(recambioHechoInDto,idRecambioHecho);
            return ResponseEntity.ok(recambioHecho);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/recambio/findRecambioHechosByPrescripcionDiaAndFecha/{fecha}")
    public ResponseEntity<List<RecambioHecho>>  findRecambioHechosByPrescripcionDiaAndFecha(@RequestBody List<Recambio> recambios,@PathVariable("fecha") Date fecha){
        try{
            List<RecambioHecho> recambioHechos=new ArrayList<>();
            LocalDate fecha2 = Instant.ofEpochMilli(fecha.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            for (Recambio recambio:recambios) {
                recambioHechos.add(this.recambioHechoService.findByRecambioAndFecha(recambio.getIdRecambio(),fecha2));
            }
            return ResponseEntity.ok(recambioHechos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/prescripcion/findRecambioHechoById/{idRecambioHecho}")
    public ResponseEntity<RecambioHecho> findRecambioHechoById(@PathVariable int idRecambioHecho){
        try{
            RecambioHecho recambio=this.recambioHechoService.findRecambioById(idRecambioHecho);
            return ResponseEntity.ok(recambio);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
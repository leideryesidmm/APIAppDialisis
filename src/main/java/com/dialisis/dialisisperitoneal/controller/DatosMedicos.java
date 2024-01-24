package com.dialisis.dialisisperitoneal.controller;

import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.service.*;
import com.dialisis.dialisisperitoneal.service.dto.*;
import com.dialisis.dialisisperitoneal.service.dto.uniones.UnionPacienteAlergiaInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/DatosMedicos")
public class DatosMedicos {
    private final EpsService epsService;
    private final FormulaMedicamentoService formulaMedicamentoService;
    private final PacienteAlergiaService pacienteAlergiaService;
    private final AlergiaService alergiaService;
    private final ViaAdministracionService viaAdministracionService;

    public DatosMedicos(EpsService epsService, FormulaMedicamentoService formulaMedicamentoService, PacienteAlergiaService pacienteAlergiaService, AlergiaService alergiaService, ViaAdministracionService viaAdministracionService) {
        this.epsService = epsService;
        this.formulaMedicamentoService = formulaMedicamentoService;
        this.pacienteAlergiaService = pacienteAlergiaService;
        this.alergiaService = alergiaService;
        this.viaAdministracionService = viaAdministracionService;
    }
    @PatchMapping("formulaMedicamento/actualizar/{id_formula_medicamento}")
    public ResponseEntity<Void> actualizarFormulaMedicamento(@PathVariable("id_formula_medicamento")int idFormulaMedicamento,
                                                             @RequestBody FormulaMedicamentoInDto formulaMedicamentoInDto){
        this.formulaMedicamentoService.actualizarFormula(idFormulaMedicamento,formulaMedicamentoInDto);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/alergia/crear")
    public void crearAlergia(@RequestBody UnionPacienteAlergiaInDto unionPacienteAlergiaInDto){
        Alergia alergia= this.alergiaService.crearAlergia(unionPacienteAlergiaInDto.getAlergiaInDto());
        agregarAlergiaByPaciente(unionPacienteAlergiaInDto, alergia.getIdAlergia());
    }
    @PostMapping("/alergia/agregar/")
    public PacienteAlergia agregarAlergiaByPaciente(@RequestBody UnionPacienteAlergiaInDto unionPacienteAlergiaInDto, int id){
        PacienteAlergiaInDto pacienteAlergiaInDto = new PacienteAlergiaInDto();
        pacienteAlergiaInDto.setAlergia(id);
        pacienteAlergiaInDto.setPaciente(unionPacienteAlergiaInDto.getPacienteInDto().getCedula());
        return this.pacienteAlergiaService.crearPacienteAlergia(pacienteAlergiaInDto);
    }
    @PostMapping("/alergia/listByPaciente")
    public ResponseEntity<List<PacienteAlergia>> listarAlergiasPorPaciente(@RequestBody PacienteInDto pacienteInDto){
        List<PacienteAlergia> pacienteAlergia= pacienteAlergiaService.findAllByPaciente(pacienteInDto.getCedula());
        if(pacienteAlergia ==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacienteAlergia);
    }
    @PatchMapping("/alergia/editar/{id_alergia}")
    public void editarAlergia(@PathVariable("id_alergia") int idAlergia, @RequestBody AlergiaInDto alergiaInDto){
        this.alergiaService.actualizarAlergia(idAlergia, alergiaInDto);
    }
    @PatchMapping("/alergia/inactivar")
    public void inactivarAlergia(@RequestBody UnionPacienteAlergiaInDto unionPacienteAlergiaInDto) {
        this.pacienteAlergiaService.inactivarAlergia(unionPacienteAlergiaInDto.getPacienteInDto().getCedula(),unionPacienteAlergiaInDto.getAlergiaInDto().getIdAlergia());
    }
    public PacienteAlergia findPacienteAlergia(@PathVariable("cedula")String cedula,@PathVariable("id_alergia")int idAlergia){
        return this.pacienteAlergiaService.findAlergiaPorPaciente(cedula,idAlergia);
    }
    @PatchMapping("/alergia/activar/{cedula}{id_alergia}")
    public void activarAlergia(@PathVariable("cedula")String cedula,@PathVariable("id_alergia")int idAlergia) {
        this.pacienteAlergiaService.activarAlergia(cedula,idAlergia);
    }
    @GetMapping("/alergia/ListPasadas/{cedula}")
    public List<PacienteAlergia> findAlergiasPasadas(@PathVariable("cedula")String cedula){
        return this.pacienteAlergiaService.findAlergiasPasadas(cedula);
    }

    @PostMapping("/medicamento/findMedicamentoByPaciente")
    public List<FormulaMedicamento> findFormulaByPaciente(@RequestBody PacienteInDto pacienteInDto){
        return this.formulaMedicamentoService.findAllByPaciente(pacienteInDto.getCedula());
    }
    @PostMapping("/Medicamento/crear")
    public FormulaMedicamento crearFormulaMedicamento(@RequestBody FormulaMedicamento formulaMedicamentoInDto) {
        try {
            return this.formulaMedicamentoService.crearFormulaMedicamento(formulaMedicamentoInDto);
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
    @GetMapping("/medicamento/findById/{id}")
    public FormulaMedicamento findMedicamento(@PathVariable int id) {
        return this.formulaMedicamentoService.findById(id);
    }
    @GetMapping("medicamento/viaAdministracion")
    public List<ViaAdministracion> viaAdministracionList(){
        return this.viaAdministracionService.findAll();
    }
    @PostMapping("/medicamento/viaAdministración/crear")
    public void crearViaAdministracion(@RequestBody ViaAdministracionInDto viaAdministracionInDto){
        this.viaAdministracionService.createoViaAdministracion(viaAdministracionInDto);
    }
    @PostMapping("/medicamento/viaAdministración/actualizar/{id}")
    public void crearViaAdministracion(@PathVariable("id") int id,@RequestBody ViaAdministracionInDto viaAdministracionInDto){
        this.viaAdministracionService.updateViaAdministracion(id,viaAdministracionInDto);
    }
    @DeleteMapping("/medicamento/eliminar/{id_medicamento}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable("id_medicamento") int idMedicamento) {
        this.formulaMedicamentoService.deleteById(idMedicamento);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/ListEps")
    public List<Eps> findAllEps() {
        return this.epsService.findAllEps();
    }
}

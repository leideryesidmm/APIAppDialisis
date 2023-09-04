package com.Dialisis.DialisisPeritoneal.controller;

import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Prueba")
public class PruebaController {

    private final AlergiaService alergiaService;
    private final CitaService citaService;
    private final ClinicaService clinicaService;
    private final CormobilidadService cormobilidadService;
    private final ParentescoService parentescoService;
    private final FormulaMedicamentoService formulaMedicamentoService;
    private final ProgramarMedicamentoService programarMedicamentoService;
    private final TomaMedicamentoService tomaMedicamentoService;
    private final PacienteAlergiaService pacienteAlergiaService;
    //private final AlimentacionPacienteService alimentacionPacienteService;
    private final EnfermedadService enfermedadService;
    private final CuidadorService cuidadorService;
    private final CuidadorPacienteService cuidadorPacienteService;
    private  final EpsService epsService;

    public PruebaController(EpsService epsService, AlergiaService alergiaService, CitaService citaService, ClinicaService clinicaService, CormobilidadService cormobilidadService, ParentescoService parentescoService,  FormulaMedicamentoService formulaMedicamentoService, ProgramarMedicamentoService programarMedicamentoService, TomaMedicamentoService tomaMedicamentoService, PacienteAlergiaService pacienteAlergiaService, /*AlimentacionPacienteService alimentacionPacienteService,*/ EnfermedadService enfermedadService, CuidadorService cuidadorService, CuidadorPacienteService cuidadorPacienteService) {
        this.alergiaService = alergiaService;
        this.citaService = citaService;
        this.epsService= epsService;
        this.clinicaService = clinicaService;
        this.cormobilidadService = cormobilidadService;
        this.parentescoService = parentescoService;
        this.formulaMedicamentoService = formulaMedicamentoService;
        this.programarMedicamentoService = programarMedicamentoService;
        this.tomaMedicamentoService = tomaMedicamentoService;
        this.pacienteAlergiaService = pacienteAlergiaService;
        //this.alimentacionPacienteService = alimentacionPacienteService;
        this.enfermedadService = enfermedadService;
        this.cuidadorService = cuidadorService;
        this.cuidadorPacienteService = cuidadorPacienteService;
    }

    //Métodos de crear
    @PostMapping("/Alergia")
    public Alergia crearAlergia(@RequestBody AlergiaInDto alergiaInDto) {
        return this.alergiaService.crearAlergia(alergiaInDto);
    }


    @PostMapping("/Cita")
    public Cita crearCita(@RequestBody CitaInDto citaInDto) {
        return this.citaService.crearCita(citaInDto);
    }

    @PostMapping("/Clinica")
    public Clinica crearClinica(@RequestBody ClinicaInDto clinicaInDto) {
        return this.clinicaService.crearClinica(clinicaInDto);
    }

    @PostMapping("/Cormobilidad")
    public Cormobilidad crearCormobilidad(@RequestBody CormobilidadInDto cormobilidadInDto) {
        return this.cormobilidadService.crearCormobilidad(cormobilidadInDto);
    }

    @PostMapping("Parentesco")
    public Parentesco crearParentesco(@RequestBody ParentescoInDto parentescoInDto) {
        return this.parentescoService.crearParentesco(parentescoInDto);
    }



    @PostMapping("FormulaMedicamento")
    public FormulaMedicamento crearFormulaMedicamento(@RequestBody FormulaMedicamentoInDto formulaMedicamentoInDto) {
        return this.formulaMedicamentoService.crearFormulaMedicamento(formulaMedicamentoInDto);
    }

    @PostMapping("ProgramarMedicamento")
    public ProgramarMedicamento crearProgramarMedicamento(@RequestBody ProgramarMedicamentoInDto programarMedicamentoInDto) {
        return this.programarMedicamentoService.crearProgramarMedicamento(programarMedicamentoInDto);
    }

    /*@PostMapping("TomaMedicamento")
    public TomaMedicamento crearTomaMedicamento(@RequestBody TomaMedicamentoInDto tomaMedicamentoInDto) {
        return this.tomaMedicamentoService.crearTomaMedicamento(tomaMedicamentoInDto);
    }*/


    @PostMapping("PacienteAlergia")
    public PacienteAlergia crearPacienteAlergia(@RequestBody PacienteAlergiaInDto pacienteAlergiaInDto){
        return this.pacienteAlergiaService.crearPacienteAlergia(pacienteAlergiaInDto);
    }

    /*@PostMapping("AlimentacionPaciente")
    public AlimentacionPaciente crearAlimentacionPaciente(@RequestBody AlimentacionPacienteInDto alimentacionPacienteInDto){
        return this.alimentacionPacienteService.crearAlimentacionPaciente(alimentacionPacienteInDto);
    }*/

    @PostMapping("/enfermedad")
    public Enfermedad crearEnfermedad(@RequestBody EnfermedadInDto enfermedadInDto){
        return this.enfermedadService.crearEnfermedad(enfermedadInDto);
    }

    @PostMapping("/cuidador")
    public Cuidador crearoActualizarCuidador(@RequestBody CuidadorInDto cuidadorInDto){
        return this.cuidadorService.crearoActualizarCuidador(cuidadorInDto);
    }



    @PostMapping("/Cuidador_Paciente")
    public CuidadorPaciente crearCuidadorPaciente(@RequestBody CuidadorPacienteInDto cuidadorpacienteInDto) {
        return this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorpacienteInDto);
    }

    //Métodos Listar
    @GetMapping("/ListAlergia")
    public List<Alergia> findAllAlergia() {
        return this.alergiaService.findAll();
    }




    @GetMapping("/ListClinica")
    public List<Clinica> findAllClinica() {
        return this.clinicaService.findAll();
    }

    @GetMapping("/ListCormobilidad")
    public List<Cormobilidad> findAllCormobilidad() {
        return this.cormobilidadService.findAll();
    }

    @GetMapping("/ListParentesco")
    public List<Parentesco> findAllParentesco() {
        return this.parentescoService.findAll();
    }

    @GetMapping("/ListEps")
    public List<Eps> findAllEps() {
        return this.epsService.findAllEps();
    }


    @GetMapping("ListFormulaMedicamento")
    public List<FormulaMedicamento> findAllFormulaMed() {
        return this.formulaMedicamentoService.findAll();
    }

    @GetMapping("/ListProgramarMedicamento")
    public List<ProgramarMedicamento> findAllProgramarMedicamento() {
        return this.programarMedicamentoService.findAll();
    }

    @GetMapping("/ListTomaMedicamento")
    public List<TomaMedicamento> findAllTomaMedicamento() {
        return this.tomaMedicamentoService.findAll();
    }

    @GetMapping("/ListPacienteAlergia")
    public List<PacienteAlergia>findAllPacienteAlergia(){
        return this.pacienteAlergiaService.findAll();
    }

    /*@GetMapping("/ListAlimentacionPaciente")
    public List<AlimentacionPaciente> findAllAlimentacionPaciente(){
        return this.alimentacionPacienteService.findAll();
    }*/

    @GetMapping("/enfermedades")
    public List<Enfermedad> findAllEnfermedad(){
        return this.enfermedadService.findAll();
    }

    @GetMapping("/cuidador")
    public List<Cuidador> findAllCuidadores(){
        return this.cuidadorService.findAll();
    }


    @GetMapping("/ListCuidador_Paciente")
    public List<CuidadorPaciente> findAllCuidadorPaciente(){
        return this.cuidadorPacienteService.findAll();
    }

    //Métodos buscar por Id
    @GetMapping("/Alergia/Id/{id_alergia}")
    public Alergia findById(@PathVariable("id_alergia") int id_alergia) {
        return this.alergiaService.findById(id_alergia);
    }



    @GetMapping("/Cita/Id/{id_cita}")
    public Cita findByIdCita(@PathVariable("id_cita") int id_cita) {
        return this.citaService.findById(id_cita);
    }

    @GetMapping("/Clinica/Id/{id_clinica}")
    public Clinica findByIdClinica(@PathVariable("id_clinica") int id_clinica) {
        return this.clinicaService.findById(id_clinica);
    }

    @GetMapping("/Cormobilidad/Id/{id_cormobilidad}")
    public Cormobilidad findByIdCormobilidad(@PathVariable("id_cormobilidad") int id_cormobilidad) {
        return this.cormobilidadService.findById(id_cormobilidad);
    }

    @GetMapping("/Parentesco/Id/{id_parentesco}")
    public Parentesco findByIdParentesco(@PathVariable("id_parentesco") int id_parenetsco) {
        return this.parentescoService.findById(id_parenetsco);
    }



    @GetMapping("/FormulaMedicamento/Id/{id_formula_medicamento}")
    public FormulaMedicamento findByIdFormula(@PathVariable("id_formula_medicamento") int id_formula_medicamento) {
        return this.formulaMedicamentoService.findById(id_formula_medicamento);
    }

    @GetMapping("/ProgramarMedicamento/Id/{id_programar_medicamento}")
    public ProgramarMedicamento findByIdProgramar(@PathVariable("id_programar_medicamento") int id_programar_medicamento) {
        return this.programarMedicamentoService.findById(id_programar_medicamento);
    }

    @GetMapping("/TomaMedicamento/Id/{id_toma_medicamento}")
    public TomaMedicamento findByIdTomaMedicamento(@PathVariable("id_toma_medicamento") int id_toma_medicamento) {
        return this.tomaMedicamentoService.findById(id_toma_medicamento);
    }



    @GetMapping("/PacienteAlergia/Id/{id_paciente_alergia}")
    public PacienteAlergia findByIdPacienteAlergia(@PathVariable("id_paciente_alergia")int id_paciente_alergia){
        return this.pacienteAlergiaService.findById(id_paciente_alergia);
    }

    /*@GetMapping("/AlimentacionPaciente/id/{id_alimentacion_paciente}")
    public AlimentacionPaciente findByIdAlimentacionPaciente(@PathVariable("id_alimentacion_paciente")int id_alimentacion_paciente){
        return this.alimentacionPacienteService.findById(id_alimentacion_paciente);
    }*/

    @GetMapping("/enfermedad/{id_enfermedad}")
    public Enfermedad findByIdEnfermedad(int id_enfermedad){
        return this.enfermedadService.findById(id_enfermedad);
    }

    @GetMapping("/cuidador/{cedula}")
    public Cuidador findAllCuidadores(String cedula){
        return this.cuidadorService.findAllBycedula(cedula);
    }



    @GetMapping("/CuidadorPaciente/{id_cuidador_paciente}")
    public CuidadorPaciente findByIdCuidadorPaciente(@PathVariable("id_cuidador_paciente")int id_cuidador_paciente){
        return this.cuidadorPacienteService.findById(id_cuidador_paciente);
    }
    //Actualizar datos


    /*@PatchMapping("/ActualizarAlergia/{descripcion},{id_alergia}")
    public ResponseEntity<Void> actualizarAlergia(@PathVariable("descripcion") String descripcion,
                                                  @PathVariable("id_alergia") int id_alergia) {
        this.alergiaService.actualizarAlergia(descripcion, id_alergia);
        return ResponseEntity.noContent().build();
    }
*/

    @PatchMapping("ActualizarClinica/{id_clinica},{nombre},{direccion}")
    public ResponseEntity<Void> actualizarClinica(@PathVariable("id_clinica")int id_clinica,
                                                  @PathVariable("nombre")String nombre,
                                                  @PathVariable("direccion")String direccion){
        this.clinicaService.actualizarClinica(id_clinica,nombre,direccion);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("ActualizarCormobilidad/{id_cormobilidad},{paciente},{enfermedad}")
    public ResponseEntity<Void> actualizarCormobilidad(@PathVariable("id_cormobilidad")int id_cormobilidad,
                                                       @PathVariable("paciente")long paciente,
                                                       @PathVariable("enfermedad")int enfermedad){
        this.cormobilidadService.actualizarCormobilidad(id_cormobilidad,paciente,enfermedad);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("ActualizarParentesco/{id_parentesco}, {descripcion}")
    public ResponseEntity<Void> actualizarParentesco(@PathVariable("id_parentesco")int id_parentesco,
                                                     @PathVariable("descripcion")String descripcion){
        this.parentescoService.actualizarParentesco(id_parentesco,descripcion);
        return ResponseEntity.noContent().build();
    }



    @PatchMapping("ActualizarFormulaMedicamento/{id_formula_medicamento}")
    public ResponseEntity<Void> actualizarFormulaMedicamento(@PathVariable("id_formula_medicamento")int id_formula_medicamento,
                                                             @RequestBody FormulaMedicamentoInDto formulaMedicamentoInDto){
        this.formulaMedicamentoService.actualizarFormula(id_formula_medicamento,formulaMedicamentoInDto);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/ActualizarProgramarMedicamento/{id_programar_Medicamento},{fecha_ini},{fecha_fin}")
    public ResponseEntity<Void> actualizarProgramarMedicamento(@PathVariable("id_programar_medicamento")int id_programar_medicamento,
                                                               @PathVariable("fecha_ini")Date fecha_ini,
                                                               @PathVariable("fecha_fin")Date fecha_fin){
    this.programarMedicamentoService.actualizarProgramarMedicamento(id_programar_medicamento,fecha_ini,fecha_fin);
    return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ActualizarTomaMedicamento/{id_tipo_medicamento},{hora},{tomado}")
    public ResponseEntity<Void> actualizarTomaMedicamento(@PathVariable("id_tipo_medicamento")int id_tipo_medicamento,
                                                          @PathVariable("hora")Date hora,
                                                          @PathVariable("tomado")boolean tomado){
        this.tomaMedicamentoService.actualizarTomaMedicamento(id_tipo_medicamento,hora,tomado);
        return ResponseEntity.noContent().build();
    }


    /*@PatchMapping("/ActualizarPacienteAlergia/{id_paciente_alergia},{paciente},{alergia}")
    public ResponseEntity<Void> actualizarPacienteAlergia(@PathVariable("id_paciente_alergia")int id_paciente_alergia,
                                                          @PathVariable("paciente")long paciente,
                                                          @PathVariable("alergia")int alergia){
        this.pacienteAlergiaService.actualizarPacienteAlergia(id_paciente_alergia,paciente,alergia);
        return ResponseEntity.noContent().build();
    }*/

    /*@PatchMapping("/ActualizarAlimentacionPaciente/{id_alimentacion_paciente},{alimentacion},{jornada},{fecha_hora},{cantidad}")
    public ResponseEntity<Void>actualizarAlimentacionPaciente(@PathVariable("id_alimentacion_paciente")int id_alimentacion_paciente,
                                                              @PathVariable("alimentacion")int alimentacion,
                                                              @PathVariable("jornada")int jornada,
                                                              @PathVariable("fecha_hora")Date fecha_hora,
                                                              @PathVariable("cantidad")int cantidad){
        this.alimentacionPacienteService.actualizarAlimentacionPaciente(id_alimentacion_paciente,alimentacion,jornada,fecha_hora,cantidad);
        return ResponseEntity.noContent().build();
        }*/
    @PatchMapping("/enfermedad/{id_enfermedad},{nombre}")
    public void actualizarEnfermedad(int id_enfermedad,String nombre){
        this.enfermedadService.actualizarEnfermedad(id_enfermedad,nombre);
    }

    @PatchMapping("/CuidadorPaciente/{id_cuidador_paciente}, {fecha_ini},{fecha_fin}")
    public ResponseEntity<Void> actualizarCuidadorPaciente(@PathVariable("id_cuidador_paciente")int id_cuidador_paciente,
                                                           @PathVariable("fecha_ini") Date fecha_ini,
                                                           @PathVariable("fecha_fin") Date fecha_fin,
                                                           @PathVariable("activo") boolean activo){
        this.cuidadorPacienteService.actualizarCuidadorPaciente(id_cuidador_paciente,fecha_ini,fecha_fin, activo);
        return ResponseEntity.noContent().build();
    }


    //Métodos de eliminar

}
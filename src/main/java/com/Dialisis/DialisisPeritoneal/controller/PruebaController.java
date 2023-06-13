package com.Dialisis.DialisisPeritoneal.controller;

import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Prueba")
public class PruebaController {

    private final AlergiaService alergiaService;
    private final CitaService citaService;
    private final ClinicaService clinicaService;
    private final ComorbilidadService comorbilidadService;
    private final ParentescoService parentescoService;
    private final TipoRecambioService tipoRecambioService;
    private final FormulaMedicamentoService formulaMedicamentoService;
    private final ProgramarMedicamentoService programarMedicamentoService;
    private final TomaMedicamentoService tomaMedicamentoService;
    private final MedicoClinicaService medicoClinicaService;
    private final PacienteAlergiaService pacienteAlergiaService;
    //private final AlimentacionPacienteService alimentacionPacienteService;
    private final EnfermedadService enfermedadService;
    private final CuidadorService cuidadorService;
    private final JornadaService jornadaService;
    private final CuidadorPacienteService cuidadorPacienteService;

    public PruebaController(AlergiaService alergiaService, CitaService citaService, ClinicaService clinicaService, ComorbilidadService comorbilidadService, ParentescoService parentescoService, TipoRecambioService tipoRecambioService, FormulaMedicamentoService formulaMedicamentoService, ProgramarMedicamentoService programarMedicamentoService, TomaMedicamentoService tomaMedicamentoService, MedicoClinicaService medicoClinicaService, PacienteAlergiaService pacienteAlergiaService, /*AlimentacionPacienteService alimentacionPacienteService,*/ EnfermedadService enfermedadService, CuidadorService cuidadorService, JornadaService jornadaService, CuidadorPacienteService cuidadorPacienteService) {
        this.alergiaService = alergiaService;
        this.citaService = citaService;
        this.clinicaService = clinicaService;
        this.comorbilidadService = comorbilidadService;
        this.parentescoService = parentescoService;
        this.tipoRecambioService = tipoRecambioService;
        this.formulaMedicamentoService = formulaMedicamentoService;
        this.programarMedicamentoService = programarMedicamentoService;
        this.tomaMedicamentoService = tomaMedicamentoService;
        this.medicoClinicaService = medicoClinicaService;
        this.pacienteAlergiaService = pacienteAlergiaService;
        //this.alimentacionPacienteService = alimentacionPacienteService;
        this.enfermedadService = enfermedadService;
        this.cuidadorService = cuidadorService;
        this.jornadaService = jornadaService;
        this.cuidadorPacienteService = cuidadorPacienteService;
    }

    //Métodos de crear
    @PostMapping("/Allergy")
    public Alergia crearAlergia(@RequestBody AlergiaInDto alergiaInDto) {
        return this.alergiaService.crearAlergia(alergiaInDto);
    }


    @PostMapping("/Appointment")
    public Cita crearCita(@RequestBody CitaInDto citaInDto) {
        return this.citaService.crearCita(citaInDto);
    }

    @PostMapping("/Clinic")
    public Clinica crearClinica(@RequestBody ClinicaInDto clinicaInDto) {
        return this.clinicaService.crearClinica(clinicaInDto);
    }

    @PostMapping("/Comorbility")
    public Comorbilidad crearComorbilidad(@RequestBody ComorbilidadInDto comorbilidadInDto) {
        return this.comorbilidadService.crearComorbilidad(comorbilidadInDto);
    }

    @PostMapping("Relationship")
    public Parentesco crearParentesco(@RequestBody ParentescoInDto parentescoInDto) {
        return this.parentescoService.crearParentesco(parentescoInDto);
    }

    @PostMapping("Tipo_Recambio")
    public TipoRecambio crearTipoRecambio(@RequestBody TipoRecambioInDto tipoRecambioInDto) {
        return this.tipoRecambioService.crearTipoRecambio(tipoRecambioInDto);
    }

    @PostMapping("FormuleMedicine")
    public FormulaMedicamento crearFormulaMedicamento(@RequestBody FormulaMedicamentoInDto formulaMedicamentoInDto) {
        return this.formulaMedicamentoService.crearFormulaMedicamento(formulaMedicamentoInDto);
    }

    @PostMapping("ProgrameMedicine")
    public ProgramarMedicamento crearProgramarMedicamento(@RequestBody ProgramarMedicamentoInDto programarMedicamentoInDto) {
        return this.programarMedicamentoService.crearProgramarMedicamento(programarMedicamentoInDto);
    }

    /*@PostMapping("TomaMedicamento")
    public TomaMedicamento crearTomaMedicamento(@RequestBody TomaMedicamentoInDto tomaMedicamentoInDto) {
        return this.tomaMedicamentoService.crearTomaMedicamento(tomaMedicamentoInDto);
    }*/

    @PostMapping("DoctorClinic")
    public MedicoClinica crearMedicoClinica(@RequestBody MedicoClinicaInDto medicoClinicaInDto) {
        return this.medicoClinicaService.crearMedicoClinica(medicoClinicaInDto);
    }

    @PostMapping("PacientAllergy")
    public PacienteAlergia crearPacienteAlergia(@RequestBody PacienteAlergiaInDto pacienteAlergiaInDto){
        return this.pacienteAlergiaService.crearPacienteAlergia(pacienteAlergiaInDto);
    }

    /*@PostMapping("AlimentacionPaciente")
    public AlimentacionPaciente crearAlimentacionPaciente(@RequestBody AlimentacionPacienteInDto alimentacionPacienteInDto){
        return this.alimentacionPacienteService.crearAlimentacionPaciente(alimentacionPacienteInDto);
    }*/

    @PostMapping("/disease")
    public Enfermedad crearEnfermedad(@RequestBody EnfermedadInDto enfermedadInDto){
        return this.enfermedadService.crearEnfermedad(enfermedadInDto);
    }

    @PostMapping("/carer")
    public Cuidador crearoActualizarCuidador(@RequestBody CuidadorInDto cuidadorInDto){
        return this.cuidadorService.crearoActualizarCuidador(cuidadorInDto);
    }

    @PostMapping("/jornada")
    public Jornada crearJornada(@RequestBody JornadaInDto jornadaInDto){
        return this.jornadaService.crearJornada(jornadaInDto);
    }

    @PostMapping("/CarerPacient")
    public CuidadorPaciente crearCuidadorPaciente(@RequestBody CuidadorPacienteInDto cuidadorpacienteInDto) {
        return this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorpacienteInDto);
    }

    //Métodos Listar
    @GetMapping("/ListAllergy")
    public List<Alergia> findAllAlergia() {
        return this.alergiaService.findAll();
    }




    @GetMapping("/ListClinic")
    public List<Clinica> findAllClinica() {
        return this.clinicaService.findAll();
    }

    @GetMapping("/ListCormobility")
    public List<Comorbilidad> findAllCormobilidad() {
        return this.comorbilidadService.findAll();
    }

    @GetMapping("/ListRelationShip")
    public List<Parentesco> findAllParentesco() {
        return this.parentescoService.findAll();
    }

    @GetMapping("/ListTipoRecambio")
    public List<TipoRecambio> findAllTipoRecambio() {
        return this.tipoRecambioService.findAll();
    }

    @GetMapping("ListFormuleMedicine")
    public List<FormulaMedicamento> findAllFormulaMed() {
        return this.formulaMedicamentoService.findAll();
    }

    @GetMapping("/ListProgrameMedicine")
    public List<ProgramarMedicamento> findAllProgramarMedicamento() {
        return this.programarMedicamentoService.findAll();
    }

    @GetMapping("/ListTakeMedicine")
    public List<TomaMedicamento> findAllTomaMedicamento() {
        return this.tomaMedicamentoService.findAll();
    }

    @GetMapping("/ListDoctorClinic")
    public List<MedicoClinica> findAllMedicoClinica() {
        return this.medicoClinicaService.findAll();
    }

    @GetMapping("/ListPacientAllergy")
    public List<PacienteAlergia>findAllPacienteAlergia(){
        return this.pacienteAlergiaService.findAll();
    }

    /*@GetMapping("/ListAlimentacionPaciente")
    public List<AlimentacionPaciente> findAllAlimentacionPaciente(){
        return this.alimentacionPacienteService.findAll();
    }*/

    @GetMapping("/diseases")
    public List<Enfermedad> findAllEnfermedad(){
        return this.enfermedadService.findAll();
    }

    @GetMapping("/carer")
    public List<Cuidador> findAllCuidadores(){
        return this.cuidadorService.findAll();
    }

    @GetMapping("/jornadas")
    public List<Jornada> findAllJornada(){
        return this.jornadaService.findAll();
    }

    @GetMapping("/ListCarerPacient")
    public List<CuidadorPaciente> findAllCuidadorPaciente(){
        return this.cuidadorPacienteService.findAll();
    }

    //Métodos buscar por Id
    @GetMapping("/Allergy/Id/{idAllergy}")
    public Alergia findById(@PathVariable("idAlergia") int idAlergia) {
        return this.alergiaService.findById(idAlergia);
    }



    @GetMapping("/Appointment/Id/{idAppointment}")
    public Cita findByIdCita(@PathVariable("idCita") int idCita) {
        return this.citaService.findById(idCita);
    }

    @GetMapping("/Clinic/Id/{idClinic}")
    public Clinica findByIdClinica(@PathVariable("idClinica") int idClinica) {
        return this.clinicaService.findById(idClinica);
    }

    @GetMapping("/Comorbility/Id/{idCormobility}")
    public Comorbilidad findByIdCormobilidad(@PathVariable("idComorbilidad") int idCormobilidad) {
        return this.comorbilidadService.findById(idCormobilidad);
    }

    @GetMapping("/Relationship/Id/{idRelationship}")
    public Parentesco findByIdParentesco(@PathVariable("idParentesco") int idParenetsco) {
        return this.parentescoService.findById(idParenetsco);
    }

    @GetMapping("/TipoRecambio/Id/{id_tipo}")
    public TipoRecambio findByIdTipoRecambio(@PathVariable("id_tipo") int id_tipo) {
        return this.tipoRecambioService.findById(id_tipo);
    }

    @GetMapping("/FormuleMedicine/Id/{idFormuleMmedicine}")
    public FormulaMedicamento findByIdFormula(@PathVariable("idFormulaMedicamento") int idFormulaMedicamento) {
        return this.formulaMedicamentoService.findById(idFormulaMedicamento);
    }

    @GetMapping("/ProgrameMedicine/Id/{idPrograMedicine}")
    public ProgramarMedicamento findByIdProgramar(@PathVariable("idProgramarMedicamento") int idProgramarMedicamento) {
        return this.programarMedicamentoService.findById(idProgramarMedicamento);
    }

    @GetMapping("/TakeMedicine/Id/{idTakeMedicine}")
    public TomaMedicamento findByIdTomaMedicamento(@PathVariable("idTomaMedicamento") int idTomaMedicamento) {
        return this.tomaMedicamentoService.findById(idTomaMedicamento);
    }

    @GetMapping("/DoctorClinic/Id/{idDoctorClinic}")
    public MedicoClinica findByIdMedicoClinica(@PathVariable("idMedicoClinica")int idMedicoClinica){
        return this.medicoClinicaService.findById(idMedicoClinica);
    }

    @GetMapping("/PacientAllergy/Id/{idPacientAllergy}")
    public PacienteAlergia findByIdPacienteAlergia(@PathVariable("idPacienteAlergia")int idPacienteAlergia){
        return this.pacienteAlergiaService.findById(idPacienteAlergia);
    }

    /*@GetMapping("/AlimentacionPaciente/id/{id_alimentacion_paciente}")
    public AlimentacionPaciente findByIdAlimentacionPaciente(@PathVariable("id_alimentacion_paciente")int id_alimentacion_paciente){
        return this.alimentacionPacienteService.findById(id_alimentacion_paciente);
    }*/

    @GetMapping("/disease/{idDiseased}")
    public Enfermedad findByIdEnfermedad(int idEnfermedad){
        return this.enfermedadService.findById(idEnfermedad);
    }

    @GetMapping("/cuidador/{cedula}")
    public Cuidador findAllCuidadores(String cedula){
        return this.cuidadorService.findAllBycedula(cedula);
    }

    @GetMapping("/Jornada/Id/{id_jornada}")
    public Jornada findByIdJornada(@PathVariable("id_jornada")int id_jornada){
        return this.jornadaService.findById(id_jornada);
    }

    @GetMapping("/CarerPacient/{idCarerPacient}")
    public CuidadorPaciente findByIdCuidadorPaciente(@PathVariable("idCuidadorPaciente")int idCuidadorPaciente){
        return this.cuidadorPacienteService.findById(idCuidadorPaciente);
    }
    //Actualizar datos


    @PatchMapping("/UpdateAllergy/{description},{idAllergy}")
    public ResponseEntity<Void> actualizarAlergia(@PathVariable("descripcion") String descripcion,
                                                  @PathVariable("idAlergia") int idAlergia) {
        this.alergiaService.actualizarAlergia(descripcion, idAlergia);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("UpdateClinic/{idClinic},{name},{address}")
    public ResponseEntity<Void> actualizarClinica(@PathVariable("idClinica")int idClinica,
                                                  @PathVariable("nombre")String nombre,
                                                  @PathVariable("direccion")String direccion){
        this.clinicaService.actualizarClinica(idClinica,nombre,direccion);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("UpdateComorbility/{idCormobility},{pacient},{disease}")
    public ResponseEntity<Void> actualizarCormobilidad(@PathVariable("idComorbilidad")int idComorbilidad,
                                                       @PathVariable("paciente")long paciente,
                                                       @PathVariable("enfermedad")int enfermedad){
        this.comorbilidadService.actualizarCormobilidad(idComorbilidad,paciente,enfermedad);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("UpdateRelationship/{idRelationship}, {description}")
    public ResponseEntity<Void> actualizarParentesco(@PathVariable("idParentesco")int idParentesco,
                                                     @PathVariable("descripcion")String descripcion){
        this.parentescoService.actualizarParentesco(idParentesco,descripcion);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("ActualizarTipoRecambio/{id_tipo},{descripcion}")
    public ResponseEntity<Void> actualizarTipoRecambio(@PathVariable("id_tipo")int id_tipo,
                                                       @PathVariable("descripcion")String descripcion){
        this.tipoRecambioService.actualizarTipoRecambio(id_tipo,descripcion);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("UpdateFormuleMedicine/{idFormuleMedicine},{medicine},{intervaleTime},{tomes},{dosis}")
    public ResponseEntity<Void> actualizarFormulaMedicamento(@PathVariable("idFormulaMedicamento")int idFormulaMedicamento,
                                                             @PathVariable("medicamento")int medicamento,
                                                             @PathVariable("idFormulaMedicamento")int intervaloTiempo,
                                                             @PathVariable("tomas")int tomas,
                                                             @PathVariable("dosis")int dosis){
        this.formulaMedicamentoService.actualizarFormula(idFormulaMedicamento,medicamento,idFormulaMedicamento,tomas,dosis);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/UpdateProgrameMedicicine/{idProgrameMedicine},{DateStart},{Datefinish}")
    public ResponseEntity<Void> actualizarProgramarMedicamento(@PathVariable("idProgramarMedicamento")int idProgramarMedicamento,
                                                               @PathVariable("fechaInicio")Date fechaInicio,
                                                               @PathVariable("fechaFin")Date fechaFin){
    this.programarMedicamentoService.actualizarProgramarMedicamento(idProgramarMedicamento,fechaInicio,fechaFin);
    return ResponseEntity.noContent().build();
    }

    @PatchMapping("/UpdateTomeMedicine/{idTipeMedicine},{hour},{taken}")
    public ResponseEntity<Void> actualizarTomaMedicamento(@PathVariable("idTipoMedicamento")int idTipoMedicamento,
                                                          @PathVariable("hora") LocalDateTime hora,
                                                          @PathVariable("tomado")boolean tomado){
        this.tomaMedicamentoService.actualizarTomaMedicamento(idTipoMedicamento,hora,tomado);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/UpdateDoctorClinic/{idDoctorClinic},{doctor},{clinic}")
    public ResponseEntity<Void> actualizarMedicoClinica(@PathVariable("idMedicoClinica")int idMedicoClinica,
                                                        @PathVariable("medico")String medico,
                                                        @PathVariable("clinica")int clinica,
                                                        @PathVariable("activa")boolean activa){
        this.medicoClinicaService.actualizarMedicoClinica(idMedicoClinica,medico,clinica, activa);
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
    @PatchMapping("/disease/{idDisease},{name}")
    public void actualizarEnfermedad(int idEnfermedad,String nombre){
        this.enfermedadService.actualizarEnfermedad(idEnfermedad,nombre);
    }

    @PatchMapping("/CarerPacient/{idCarerPacient}, {DateStart},{DateFinish}")
    public ResponseEntity<Void> actualizarCuidadorPaciente(@PathVariable("idCuidadorPaciente")int idCuidadorPaciente,
                                                           @PathVariable("fechaInicio") Date fechaInicio,
                                                           @PathVariable("fechaFin") Date fechaFin,
                                                           @PathVariable("activo") boolean activo){
        this.cuidadorPacienteService.actualizarCuidadorPaciente(idCuidadorPaciente,fechaInicio,fechaFin, activo);
        return ResponseEntity.noContent().build();
    }


    //Métodos de eliminar
    @DeleteMapping("/EliminateAppointment/{idAppointment}")
    public ResponseEntity<Void> deleteCita(@PathVariable("idCita") int idCita) {
        this.citaService.deleteById(idCita);
        return ResponseEntity.noContent().build();
    }
}
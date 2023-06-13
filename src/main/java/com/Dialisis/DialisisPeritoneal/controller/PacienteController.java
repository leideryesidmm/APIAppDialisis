package com.Dialisis.DialisisPeritoneal.controller;

import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pacient")
public class PacienteController {
    private final PacienteService pacienteService;
    private final MedicamentoService medicamentoService;
    private final CuidadorPacienteService cuidadorPacienteService;
    private final CuidadorService cuidadorService;
    private final EnfermedadService enfermedadService;
    private final ComorbilidadService comorbilidadService;
    private final CitaService citaService;
    private final FormulaMedicamentoService formulaMedicamentoService;
    private final ProgramarMedicamentoService programarMedicamentoService;
    private final TomaMedicamentoService tomaMedicamentoService;
    private final PacienteAlergiaService pacienteAlergiaService;
    private final AlergiaService alergiaService;
    //private final AlimentacionPacienteService alimentacionPacienteService;


    public PacienteController(PacienteService pacienteService, MedicamentoService medicamentoService, CuidadorPacienteService cuidadorPacienteService, CuidadorService cuidadorService, EnfermedadService enfermedadService, ComorbilidadService comorbilidadService, CitaService citaService, FormulaMedicamentoService formulaMedicamentoService, ProgramarMedicamentoService programarMedicamentoService, TomaMedicamentoService tomaMedicamentoService, PacienteAlergiaService pacienteAlergiaService, AlergiaService alergiaService) {
        this.pacienteService = pacienteService;
        this.medicamentoService = medicamentoService;
        this.cuidadorPacienteService = cuidadorPacienteService;
        this.cuidadorService = cuidadorService;
        this.enfermedadService = enfermedadService;
        this.comorbilidadService = comorbilidadService;
        this.citaService = citaService;
        this.formulaMedicamentoService = formulaMedicamentoService;
        this.programarMedicamentoService = programarMedicamentoService;
        this.tomaMedicamentoService = tomaMedicamentoService;
        this.pacienteAlergiaService = pacienteAlergiaService;
        this.alergiaService = alergiaService;
        //this.alimentacionPacienteService = alimentacionPacienteService;
    }

    @PostMapping("/createPacient")
    public Paciente crearPaciente(@RequestBody PacienteInDto pacienteInDto){
        return this.pacienteService.crearPaciente(pacienteInDto);
    }

    @GetMapping("/findPacientByCc/{cc}")
    public Paciente findPacienteByCedula(@PathVariable("cedula")String cedula){
        return this.pacienteService.findByCedula(cedula);
    }
    @PostMapping("/medicine/createMedicine")
    public Medicamento crearMedicamento(@RequestBody MedicamentoInDto medicamentoInDto){
        return this.medicamentoService.crearMedicamento(medicamentoInDto);
    }


    @GetMapping("/medicine/listMedicine")
    public List <Medicamento> findAllMed(){
        return this.medicamentoService.encontrarMedicamentos();
    }


    @GetMapping("/medicine/byId/{idMedicine}")
    public Medicamento findById(@PathVariable("idMedicamento") int idMedicamento){
        return this.medicamentoService.findById(idMedicamento);
    }

    @PatchMapping("/update/{cc},{eps},{heigth},{weight},{dry_weight},{address},{ocupation}")
    public ResponseEntity<Void> actualizarDatosPaciente(@PathVariable("cedula")String cedula,
                                                        @PathVariable("eps") String eps,
                                                        @PathVariable("altura") int altura,
                                                        @PathVariable("peso") double peso,
                                                        @PathVariable("pesoSeco") double pesoSeco,
                                                        @PathVariable("direccion") String direccion,
                                                        @PathVariable("ocupacion") String ocupacion){
        this.pacienteService.actualizarDatosPaciente(cedula,eps,altura,peso,pesoSeco,direccion,ocupacion);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/medicine/update/{name},{concentration},{viaAdministration},{description},{idMedicine}")
    public ResponseEntity<Void> actualizarMedicamentos(@PathVariable("nombre")String nombre,
                                                       @PathVariable("concentracion")int concentracion,
                                                       @PathVariable("viaAdministracion")int viaAdministracion,
                                                       @PathVariable("descripcion")String descripcion,
                                                       @PathVariable("idMedicamento")int idMedicamento){
        this.medicamentoService.actualizarMedicamento(nombre,concentracion,viaAdministracion,descripcion,idMedicamento);
        return  ResponseEntity.noContent().build();
    }
    @GetMapping("/carer/listCarerPacientByPacient/{cc}")
    public List<CuidadorPaciente> ListarCuidadoresPorPaciente(@PathVariable("cedula")String cedula){
        return this.cuidadorPacienteService.findAllByPaciente(cedula);
    }
    @GetMapping("/carer/findCarerActive/{cc}")
    public CuidadorPaciente findCuidadorActivo(@PathVariable("cedula")String cedula){
        return this.cuidadorPacienteService.findCuidadorActivo(cedula);
    }
    @PatchMapping("/carer/reactivateCarer/{cc},{carer}")
    public void ReactivarCuidadorAntiguo(@PathVariable("cedula")String cedula, @PathVariable("cedulaCuidador")String cedulaCuidador) {
        CuidadorPaciente cuidadorPaciente = findCuidadorActivo(cedula);
        if (cuidadorPaciente != null) {
            this.cuidadorPacienteService.inactivarCuidador(cuidadorPaciente.getIdCuidadorPaciente());
        }
        CuidadorPacienteInDto cuidadorPacienteInDto = new CuidadorPacienteInDto();
        cuidadorPacienteInDto.setCuidador(cedulaCuidador);
        cuidadorPacienteInDto.setPaciente(cedula);
        LocalDate fecha_ini = LocalDate.now();
        cuidadorPacienteInDto.setFecha_ini(fecha_ini);
        this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorPacienteInDto);
    }
    @PostMapping("/carer/create/{cc}")
    public void crearCuidador(@PathVariable("cedula")String cedula, @RequestBody CuidadorInDto cuidadorInDto) {
        CuidadorPaciente cuidadorPaciente = findCuidadorActivo(cedula);
        if (cuidadorPaciente != null) {
            this.cuidadorPacienteService.inactivarCuidador(cuidadorPaciente.getIdCuidadorPaciente());
        }
        Cuidador cuidador = this.cuidadorService.crearoActualizarCuidador(cuidadorInDto);
        CuidadorPacienteInDto cuidadorPacienteInDto = new CuidadorPacienteInDto();
        cuidadorPacienteInDto.setCuidador(cuidador.getCedulaCuidador());
        cuidadorPacienteInDto.setPaciente(cedula);
        LocalDate fecha_ini = LocalDate.now();
        cuidadorPacienteInDto.setFecha_ini(fecha_ini);
        this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorPacienteInDto);
    }
    @PostMapping("/allergy/create/{cc},{allergy}")
    public void crearAlergia(@PathVariable("cedula") String cedula, @RequestBody AlergiaInDto alergiaInDto){
        Alergia alergia= this.alergiaService.crearAlergia(alergiaInDto);
        agregarAlergiaByPaciente(cedula, alergia.getIdAlergia());

    }
    @PostMapping("/allergy/add/{cc},{idAllergy}")
    public void agregarAlergiaByPaciente(@PathVariable("cedula")String cedula, @PathVariable("idAlergia")int idAlergia){
        PacienteAlergiaInDto pacienteAlergiaInDto = new PacienteAlergiaInDto();
        pacienteAlergiaInDto.setAlergia(idAlergia);
        pacienteAlergiaInDto.setPaciente(cedula);
        this.pacienteAlergiaService.crearPacienteAlergia(pacienteAlergiaInDto);
    }
    @GetMapping("/allergy/listByPacient/{CC}")
    public List<PacienteAlergia> listarAlergiasPorPaciente(@PathVariable("cedula")String cedula){
        return this.pacienteAlergiaService.findAllByPaciente(cedula);
    }

    @PatchMapping("/allergy/inactivaTE/{CC}{idAllergy}")
    public void inactivarAlergia(@PathVariable("cedula")String cedula,@PathVariable("idAlergia")int idAlergia) {
        this.pacienteAlergiaService.inactivarAlergia(cedula,idAlergia);
    }
    @GetMapping("/allergy/findPacientAllergy/{cc}/{idAllergy}")
    public PacienteAlergia findPacienteAlergia(@PathVariable("cedula")String cedula,@PathVariable("idAlergia")int idAlergia){
        return this.pacienteAlergiaService.findAlergiaPorPaciente(cedula,idAlergia);
    }

    @PatchMapping("/allergy/activaTE/{CC}{idAlergia}")
    public void activarAlergia(@PathVariable("cedula")String cedula,@PathVariable("idAlergia")int idAlergia) {
        this.pacienteAlergiaService.activarAlergia(cedula,idAlergia);
    }

    @GetMapping("/allergy/ListPast/{cc}")
    public List<PacienteAlergia> findAlergiasPasadas(@PathVariable("cedula")String cedula){
        return this.pacienteAlergiaService.findAlergiasPasadas(cedula);
    }
    @GetMapping("/disease/findPast/{cc}")
    public List<Comorbilidad> findEnfermedadesPasadas(@PathVariable("cedula")String cedula){
        return this.comorbilidadService.findEnfermedadesPasadas(cedula);
    }
    @PostMapping("/disease/create/{cc}")
    public void crearEnfermedad(@PathVariable("cedula")String cedula,@RequestBody EnfermedadInDto enfermedadInDto){
        Enfermedad enfermedad=this.enfermedadService.crearEnfermedad(enfermedadInDto);
        agregarEnfermedadByPaciente(cedula, enfermedad.getIdEnfermedad());
    }
    @PostMapping("/disease/add/{cc},{idDisease}")
    public void agregarEnfermedadByPaciente(@PathVariable("cedula")String cedula,@PathVariable("idEnfermedad")int idEnfermedad){
        ComorbilidadInDto comorbilidadInDto=new ComorbilidadInDto();
        comorbilidadInDto.setEnfermedad(idEnfermedad);
        comorbilidadInDto.setPaciente(cedula);
        this.comorbilidadService.crearComorbilidad(comorbilidadInDto);
    }
    @PatchMapping("/disease/eliminate/{idComorbidity}")
    public void eliminarEnfermedad(@PathVariable("idComorbilidad")int idComorbilidad){
        this.comorbilidadService.inactivarCormobilidad(idComorbilidad);
    }
    @PatchMapping("/disease/activate/{idComorbility}")
    public void activarCormobilidad(@PathVariable("idComorbilidad")int idComorbilidad){
        this.comorbilidadService.activarCormobilidad(idComorbilidad);
    }
    /*@PostMapping("/alimentacion/agregar")
    public void agregarAlimentacionPorPaciente(@RequestBody AlimentacionPacienteInDto alimentacionPacienteInDto){
        this.alimentacionPacienteService.crearAlimentacionPaciente(alimentacionPacienteInDto);
    }*/

    /*@GetMapping("/alimentacion/listarPorDia/{cedula},{fecha}")
    public List<AlimentacionPaciente> findAllAlimentacionPacientePorDia(@PathVariable("cedula")long cedula,@PathVariable("fecha")Date fecha){
        return ListarAlimentoPorPacienteYFecha(cedula, fecha,fecha);
    }*/

    /*@GetMapping("/alimentacion/listAllByPaciente/{cedula}")
    public List<AlimentacionPaciente> ListarAlimentacionPaciente(@PathVariable("cedula")long cedula){
        return this.alimentacionPacienteService.findAllByPaciente(cedula);
    }*/

    /*@GetMapping("/alimentacion/ListarPorRango/{cedula},{fecha1},{fecha2}")
    public List<AlimentacionPaciente>ListarAlimentoPorPacienteYFecha(@PathVariable("cedula")long cedula,@PathVariable("fecha1")Date fecha1,@PathVariable("fecha2")Date fecha2){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = fecha1.toInstant();
        LocalDate fecha_1 = instant.atZone(defaultZoneId).toLocalDate();
        Instant instant2 = fecha2.toInstant();
        LocalDate fecha_2 = instant2.atZone(defaultZoneId).toLocalDate();
        return this.alimentacionPacienteService.findAllByPacienteByRango(cedula, fecha_1,fecha_2);
    }*/

    /*@PatchMapping("/alimentacion/actualizar/{idAlimentacionPaciente}")
    public void actualizarAlimentacionPaciente(@PathVariable("idAlimentacionPaciente") int idAlimentacionPaciente,@RequestBody AlimentacionPacienteInDto alimentacionPacienteInDto){
        AlimentacionPaciente alimentacionPaciente=this.alimentacionPacienteService.findById(idAlimentacionPaciente);
        if(alimentacionPaciente!=null)
            this.alimentacionPacienteService.actualizarAlimentacionPaciente(idAlimentacionPaciente,alimentacionPacienteInDto);
    }*/
    @PostMapping("/appointment/createCita")
    public void crearCita(@RequestBody CitaInDto citaInDto){
        this.citaService.crearCita(citaInDto);
    }
    @GetMapping("/appointment/listByPacient/{cc}")
    public List<Cita> findAllCitas(@PathVariable("cedulaPaciente")String cedulaPaciente){
        Paciente paciente=new Paciente(cedulaPaciente);
        return this.citaService.findAllByPaciente(paciente);
    }
    @PatchMapping("/appointment/update/{idAppointment}")
    public void actualizarCita(@PathVariable("idCita")int idCita,@RequestBody CitaInDto citaInDto){
        this.citaService.actualizarCita(idCita, citaInDto);
    }
    @DeleteMapping("/appointment/eliminate/{idAppointmenta}")
    public void eliminarCita(@PathVariable("idCita")int idCita){
        this.citaService.deleteById(idCita);
    }
    @GetMapping("/appointment/antiques/{cc}")
    public List<Cita> findAllCitasAntiguasByPaciente(@PathVariable("cedula")String cedula){
        Paciente paciente=new Paciente(cedula);
        return this.citaService.findAllCitasAntiguasByPaciente(paciente);
    }
    @GetMapping("/appointment/future")
    public List<Cita> findAllCitasFuturasByPaciente(@PathVariable("cedula")String cedula){
        Paciente paciente=new Paciente(cedula);
        return this.citaService.findAllCitasFuturasByPaciente(paciente);
    }
    @GetMapping("/appointment/filtrePyF/{cc},{filtre}")
    public List<Cita> findAllCitasPyFByPaciente(@PathVariable("cedula")String cedula,@PathVariable("filtro")byte filtro){
        Paciente paciente=new Paciente(cedula);
        if(filtro==1)
        return this.citaService.findAllCitasFuturasByPaciente(paciente);
        else return this.citaService.findAllCitasAntiguasByPaciente(paciente);
    }
    @GetMapping("/appointment/formule/{appointment}")
    public List<FormulaMedicamento> findFormulaByCita(@PathVariable("cita")int cita){
        return this.formulaMedicamentoService.findAllByCita(cita);
    }

    @PostMapping("/appointment/formule/create")
    public FormulaMedicamento crearFormulaMedicamento(@RequestBody FormulaMedicamentoInDto formulaMedicamentoInDto) {
        return this.formulaMedicamentoService.crearFormulaMedicamento(formulaMedicamentoInDto);
    }

    @PostMapping("/appointment/formule/ProgrameMedicine")
    public List<TomaMedicamento> programarMedicamento(@RequestBody ProgramarMedicamentoInDto programarMedicamentoInDto){
        ProgramarMedicamento programarMedicamento=this.programarMedicamentoService.crearProgramarMedicamento(programarMedicamentoInDto);
        List<TomaMedicamento> tomaMedicamentoList=this.tomaMedicamentoService.crearTomas(programarMedicamento,this.formulaMedicamentoService.findById(programarMedicamento.getFormulaMedicamento().getIdFormulaMedicamento()));
        return tomaMedicamentoList;
    }

}

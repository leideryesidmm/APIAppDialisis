package com.Dialisis.DialisisPeritoneal.controller;

import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final PacienteService pacienteService;
    private final MedicamentoService medicamentoService;
    private final CuidadorPacienteService cuidadorPacienteService;
    private final CuidadorService cuidadorService;
    private final EnfermedadService enfermedadService;
    private final CormobilidadService cormobilidadService;
    private final CitaService citaService;
    private final FormulaMedicamentoService formulaMedicamentoService;
    private final ProgramarMedicamentoService programarMedicamentoService;
    private final TomaMedicamentoService tomaMedicamentoService;
    private final PacienteAlergiaService pacienteAlergiaService;
    private final AlergiaService alergiaService;
    private final AlimentacionPacienteService alimentacionPacienteService;


    public PacienteController(PacienteService pacienteService, MedicamentoService medicamentoService, CuidadorPacienteService cuidadorPacienteService, CuidadorService cuidadorService, EnfermedadService enfermedadService, CormobilidadService cormobilidadService, CitaService citaService, FormulaMedicamentoService formulaMedicamentoService, ProgramarMedicamentoService programarMedicamentoService, TomaMedicamentoService tomaMedicamentoService, PacienteAlergiaService pacienteAlergiaService, AlergiaService alergiaService, AlimentacionPacienteService alimentacionPacienteService) {
        this.pacienteService = pacienteService;
        this.medicamentoService = medicamentoService;
        this.cuidadorPacienteService = cuidadorPacienteService;
        this.cuidadorService = cuidadorService;
        this.enfermedadService = enfermedadService;
        this.cormobilidadService = cormobilidadService;
        this.citaService = citaService;
        this.formulaMedicamentoService = formulaMedicamentoService;
        this.programarMedicamentoService = programarMedicamentoService;
        this.tomaMedicamentoService = tomaMedicamentoService;
        this.pacienteAlergiaService = pacienteAlergiaService;
        this.alergiaService = alergiaService;
        this.alimentacionPacienteService = alimentacionPacienteService;
    }

    @PostMapping("/crearPaciente")
    public Paciente crearPaciente(@RequestBody PacienteInDto pacienteInDto){
        return this.pacienteService.crearPaciente(pacienteInDto);
    }

    @GetMapping("/findPacienteByCedula/{cedula}")
    public Paciente findPacienteByCedula(@PathVariable("cedula")long cedula){
        return this.pacienteService.findByCedula(cedula);
    }
    @PostMapping("/medicamento/crearMedicamento")
    public Medicamento crearMedicamento(@RequestBody MedicamentoInDto medicamentoInDto){
        return this.medicamentoService.crearMedicamento(medicamentoInDto);
    }


    @GetMapping("/medicamento/listMedimentos")
    public List <Medicamento> findAllMed(){
        return this.medicamentoService.encontrarMedicamentos();
    }


    @GetMapping("/medicamento/byId/{id_medicamento}")
    public Medicamento findById(@PathVariable("id_medicamento") int id_medicamento){
        return this.medicamentoService.findById(id_medicamento);
    }

    @PatchMapping("/actualizar/{cedula},{eps},{altura},{peso},{peso_seco},{direccion},{ocupacion}")
    public ResponseEntity<Void> actualizarDatosPaciente(@PathVariable("cedula")long cedula,
                                                        @PathVariable("eps") String eps,
                                                        @PathVariable("altura") int altura,
                                                        @PathVariable("peso") int peso,
                                                        @PathVariable("peso_seco") int peso_seco,
                                                        @PathVariable("direccion") String direccion,
                                                        @PathVariable("ocupacion") String ocupacion){
        this.pacienteService.actualizarDatosPaciente(cedula,eps,altura,peso,peso_seco,direccion,ocupacion);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/medicamento/actualizar/{nombre},{concentracion},{via_administracion},{descripcion},{id_medicamento}")
    public ResponseEntity<Void> actualizarMedicamentos(@PathVariable("nombre")String nombre,
                                                       @PathVariable("concentracion")int concentracion,
                                                       @PathVariable("via_administracion")int via_administracion,
                                                       @PathVariable("descripcion")String descripcion,
                                                       @PathVariable("id_medicamento")int id_medicamento){
        this.medicamentoService.actualizarMedicamento(nombre,concentracion,via_administracion,descripcion,id_medicamento);
        return  ResponseEntity.noContent().build();
    }
    @GetMapping("/cuidador/listCuidadorPacienteByPaciente/{cedula}")
    public List<CuidadorPaciente> ListarCuidadoresPorPaciente(long cedula){
        return this.cuidadorPacienteService.findAllByPaciente(cedula);
    }
    @GetMapping("/cuidador/findCuidadorActivo/{cedula}")
    public CuidadorPaciente findCuidadorActivo(long cedula){
        return this.cuidadorPacienteService.findCuidadorActivo(cedula);
    }
    @PatchMapping("/cuidador/reactivarCuidador/{cedula},{cuidador}")
    public void ReactivarCuidadorAntiguo(long cedula, long cuidador) {
        CuidadorPaciente cuidadorPaciente = findCuidadorActivo(cedula);
        if (cuidadorPaciente != null) {
            this.cuidadorPacienteService.inactivarCuidador(cuidadorPaciente.getId_cuidador_paciente());
        }
        CuidadorPacienteInDto cuidadorPacienteInDto = new CuidadorPacienteInDto();
        cuidadorPacienteInDto.setCuidador(cuidador);
        cuidadorPacienteInDto.setPaciente(cedula);
        LocalDate fecha_ini = LocalDate.now();
        cuidadorPacienteInDto.setFecha_ini(fecha_ini);
        this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorPacienteInDto);
    }
    @PostMapping("/cuidador/crear/{cedula}")
    public void crearCuidador(long cedula, @RequestBody CuidadorInDto cuidadorInDto) {
        CuidadorPaciente cuidadorPaciente = findCuidadorActivo(cedula);
        if (cuidadorPaciente != null) {
            this.cuidadorPacienteService.inactivarCuidador(cuidadorPaciente.getId_cuidador_paciente());
        }
        Cuidador cuidador = this.cuidadorService.crearoActualizarCuidador(cuidadorInDto);
        CuidadorPacienteInDto cuidadorPacienteInDto = new CuidadorPacienteInDto();
        cuidadorPacienteInDto.setCuidador(cuidador.getCedula());
        cuidadorPacienteInDto.setPaciente(cedula);
        LocalDate fecha_ini = LocalDate.now();
        cuidadorPacienteInDto.setFecha_ini(fecha_ini);
        this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorPacienteInDto);
    }
    @PostMapping("/alergia/crear/{cedula},{alergia}")
    public void crearAlergia(@PathVariable("cedula") long cedula, @RequestBody AlergiaInDto alergiaInDto){
        Alergia alergia= this.alergiaService.crearAlergia(alergiaInDto);
        agregarAlergiaByPaciente(cedula, alergia.getId_alergia());

    }
    @PostMapping("/alergia/agregar/{cedula},{id_alergia}")
    public void agregarAlergiaByPaciente(@PathVariable("cedula")long cedula, @PathVariable("id_alergia")int id_alergia){
        PacienteAlergiaInDto pacienteAlergiaInDto = new PacienteAlergiaInDto();
        pacienteAlergiaInDto.setAlergia(id_alergia);
        pacienteAlergiaInDto.setPaciente(cedula);
        this.pacienteAlergiaService.crearPacienteAlergia(pacienteAlergiaInDto);
    }
    @GetMapping("/alergia/listByPaciente/{cedula}")
    public List<PacienteAlergia> listarAlergiasPorPaciente(@PathVariable("cedula")long cedula){
        return this.pacienteAlergiaService.findAllByPaciente(cedula);
    }

    @PatchMapping("/alergia/inactivar/{cedula}{id_alergia}")
    public void inactivarAlergia(long cedula, int id_alergia) {
        this.pacienteAlergiaService.inactivarAlergia(cedula,id_alergia);
    }
    public PacienteAlergia findPacienteAlergia(long cedula,int id_alergia){
        return this.pacienteAlergiaService.findAlergiaPorPaciente(cedula,id_alergia);
    }

    @PatchMapping("/alergia/activar/{cedula}{id_alergia}")
    public void activarAlergia(long cedula, int id_alergia) {
        this.pacienteAlergiaService.activarAlergia(cedula,id_alergia);
    }

    @GetMapping("/alergia/ListPasadas/{cedula}")
    public List<PacienteAlergia> findAlergiasPasadas(long cedula){
        return this.pacienteAlergiaService.findAlergiasPasadas(cedula);
    }
    @GetMapping("/enfermedad/findPasadas/{cedula}")
    public List<Cormobilidad> findEnfermedadesPasadas(long cedula){
        return this.cormobilidadService.findEnfermedadesPasadas(cedula);
    }
    @PostMapping("/enfermedad/crear/{cedula}")
    public void crearEnfermedad(long cedula,@RequestBody EnfermedadInDto enfermedadInDto){
        Enfermedad enfermedad=this.enfermedadService.crearEnfermedad(enfermedadInDto);
        agregarEnfermedadByPaciente(cedula, enfermedad.getId_enfermedad());
    }
    @PostMapping("/enfermedad/añadir/{cedula},{id_enfermedad}")
    public void agregarEnfermedadByPaciente(long cedula,int id_enfermedad){
        CormobilidadInDto cormobilidadInDto=new CormobilidadInDto();
        cormobilidadInDto.setEnfermedad(id_enfermedad);
        cormobilidadInDto.setPaciente(cedula);
        this.cormobilidadService.crearCormobilidad(cormobilidadInDto);
    }
    @PatchMapping("/enfermedad/eliminar/{id_cormobilidad}")
    public void eliminarEnfermedad(int id_cormobilidad){
        this.cormobilidadService.inactivarCormobilidad(id_cormobilidad);
    }
    @PatchMapping("/enfermedad/activar/id_cormobilidad")
    public void activarCormobilidad(int id_cormobilidad){
        this.cormobilidadService.activarCormobilidad(id_cormobilidad);
    }

    @PostMapping("/alimentacion/crear/{cedula},{jornada},{cantidad},{fecha},{alimentacion}")
    public void crearAlimentacion(@PathVariable("cedula") long cedula, @PathVariable("jornada")int jornada, @PathVariable("cantidad") int cantidad, @PathVariable("fecha") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha, @PathVariable("alimentacion")String alimentacion) {
        System.out.println("entro a crear");
        AlimentacionPacienteInDto alimentacionPacienteInDto= new AlimentacionPacienteInDto();
        alimentacionPacienteInDto.setPaciente(cedula);
        alimentacionPacienteInDto.setJornada(jornada);
        alimentacionPacienteInDto.setFecha_hora(fecha);
        alimentacionPacienteInDto.setCantidad(cantidad);
        alimentacionPacienteInDto.setAlimentacion(alimentacion);
        this.agregarAlimentacionPorPaciente(alimentacionPacienteInDto);

    }
    @PostMapping("/alimentacion/agregar")
    public void agregarAlimentacionPorPaciente(@RequestBody AlimentacionPacienteInDto alimentacionPacienteInDto){
        this.alimentacionPacienteService.crearAlimentacionPaciente(alimentacionPacienteInDto);
    }

    @GetMapping("/alimentacion/listarPorDia")
    public List<AlimentacionPaciente> findAllAlimentacionPacientePorDia(long cedula, Date fecha){
        return ListarAlimentoPorPacienteYFecha(cedula, fecha,fecha);
    }

    @GetMapping("/alimentacion/listAllByPaciente/{cedula}")
    public List<AlimentacionPaciente> ListarAlimentacionPaciente(long cedula){
        return this.alimentacionPacienteService.findAllByPaciente(cedula);
    }

    @GetMapping("/alimentacion/ListarPorRango/{cedula},{fecha1},{fecha2}")
    public List<AlimentacionPaciente>ListarAlimentoPorPacienteYFecha(long cedula, Date fecha1, Date fecha2){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = fecha1.toInstant();
        LocalDate fecha_1 = instant.atZone(defaultZoneId).toLocalDate();
        Instant instant2 = fecha2.toInstant();
        LocalDate fecha_2 = instant2.atZone(defaultZoneId).toLocalDate();
        return this.alimentacionPacienteService.findAllByPacienteByRango(cedula, fecha_1,fecha_2);
    }

    @PatchMapping("/alimentacion/actualizar/{idAlimentacionPaciente}")
    public void actualizarAlimentacionPaciente(@PathVariable("idAlimentacionPaciente") int idAlimentacionPaciente,@RequestBody AlimentacionPacienteInDto alimentacionPacienteInDto){
        AlimentacionPaciente alimentacionPaciente=this.alimentacionPacienteService.findById(idAlimentacionPaciente);
        if(alimentacionPaciente!=null)
            this.alimentacionPacienteService.actualizarAlimentacionPaciente(idAlimentacionPaciente,alimentacionPacienteInDto);
    }
    @PostMapping("/cita/crearCita")
    public void crearCita(@RequestBody CitaInDto citaInDto){
        this.citaService.crearCita(citaInDto);
    }
    @GetMapping("/cita/listByPaciente")
    public List<Cita> findAllCitas(long cedula){
        Paciente paciente=new Paciente(cedula);
        return this.citaService.findAllByPaciente(paciente);
    }
    @PatchMapping("/cita/actualizar")
    public void actualizarCita(int id_cita,@RequestBody CitaInDto citaInDto){
        this.citaService.actualizarCita(id_cita, citaInDto);
    }
    @DeleteMapping("/cita/eliminar/{id_cita}")
    public void eliminarCita(int id_cita){
        this.citaService.deleteById(id_cita);
    }
    @GetMapping("/cita/antiguas")
    public List<Cita> findAllCitasAntiguasByPaciente(long cedula){
        Paciente paciente=new Paciente(cedula);
        return this.citaService.findAllCitasAntiguasByPaciente(paciente);
    }
    @GetMapping("/cita/futuras")
    public List<Cita> findAllCitasFuturasByPaciente(long cedula){
        Paciente paciente=new Paciente(cedula);
        return this.citaService.findAllCitasFuturasByPaciente(paciente);
    }
    @GetMapping("/cita/filtroPyF")
    public List<Cita> findAllCitasPyFByPaciente(long cedula,byte filtro){
        Paciente paciente=new Paciente(cedula);
        if(filtro==1)
        return this.citaService.findAllCitasFuturasByPaciente(paciente);
        else return this.citaService.findAllCitasAntiguasByPaciente(paciente);
    }
    @GetMapping("/cita/formula/{cita}")
    public List<FormulaMedicamento> findFormulaByCita(int cita){
        return this.formulaMedicamentoService.findAllByCita(cita);
    }

    @PostMapping("/citas/formula/crear")
    public FormulaMedicamento crearFormulaMedicamento(@RequestBody FormulaMedicamentoInDto formulaMedicamentoInDto) {
        return this.formulaMedicamentoService.crearFormulaMedicamento(formulaMedicamentoInDto);
    }

    @PostMapping("/cita/formula/ProgramarMedicamento")
    public List<TomaMedicamento> programarMedicamento(@RequestBody ProgramarMedicamentoInDto programarMedicamentoInDto){
        ProgramarMedicamento programarMedicamento=this.programarMedicamentoService.crearProgramarMedicamento(programarMedicamentoInDto);
        List<TomaMedicamento> tomaMedicamentoList=this.tomaMedicamentoService.crearTomas(programarMedicamento,this.formulaMedicamentoService.findById(programarMedicamento.getFormulaMedicamento().getId_formula_medicamento()));
        return tomaMedicamentoList;
    }


}

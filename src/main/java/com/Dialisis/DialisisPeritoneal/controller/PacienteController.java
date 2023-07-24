package com.Dialisis.DialisisPeritoneal.controller;

import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.*;
import com.Dialisis.DialisisPeritoneal.service.dto.Uniones.UnionCitaPrescripcionDias;
import com.Dialisis.DialisisPeritoneal.service.dto.Uniones.UnionCuidadorPacienteInDto;
import com.Dialisis.DialisisPeritoneal.service.dto.Uniones.UnionPrescripcionDiasRecambios;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private final ViaAdministracionService viaAdministracionService;
    private final PrescripcionDiaService prescripcionDiaService;
    private final RecambioService recambioService;
    //private final AlimentacionPacienteService alimentacionPacienteService;


    public PacienteController(PacienteService pacienteService, MedicamentoService medicamentoService, CuidadorPacienteService cuidadorPacienteService, CuidadorService cuidadorService, EnfermedadService enfermedadService, CormobilidadService cormobilidadService, CitaService citaService, FormulaMedicamentoService formulaMedicamentoService, ProgramarMedicamentoService programarMedicamentoService, TomaMedicamentoService tomaMedicamentoService, PacienteAlergiaService pacienteAlergiaService, AlergiaService alergiaService, ViaAdministracionService viaAdministracionService, PrescripcionDiaService prescripcionDiaService, RecambioService recambioService) {
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
        //this.alimentacionPacienteService = alimentacionPacienteService;
        this.viaAdministracionService = viaAdministracionService;
        this.prescripcionDiaService = prescripcionDiaService;
        this.recambioService = recambioService;
    }

    @PostMapping("/crearPaciente")
    public Paciente crearPaciente(@RequestBody PacienteInDto pacienteInDto){
        return this.pacienteService.crearPaciente(pacienteInDto);
    }

    @GetMapping("/findPacienteByCedula/{cedula}")
    public Paciente findPacienteByCedula(@PathVariable("cedula")String cedula){

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
    public ResponseEntity<Void> actualizarDatosPaciente(@PathVariable("cedula")String cedula,
                                                        @PathVariable("eps") String eps,
                                                        @PathVariable("altura") int altura,
                                                        @PathVariable("peso") double peso,
                                                        @PathVariable("peso_seco") double peso_seco,
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
    @PatchMapping("formulaMedicamento/actualizar/{id_formula_medicamento}")
    public ResponseEntity<Void> actualizarFormulaMedicamento(@PathVariable("id_formula_medicamento")int id_formula_medicamento,
                                                             @RequestBody FormulaMedicamentoInDto formulaMedicamentoInDto){
        this.formulaMedicamentoService.actualizarFormula(id_formula_medicamento,formulaMedicamentoInDto);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/cuidador/listCuidadorPacienteByPaciente")
    public ResponseEntity<List<CuidadorPaciente>> ListarCuidadoresPorPaciente(@RequestBody PacienteInDto pacienteInDto){

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
    public void ReactivarCuidadorAntiguo(@RequestBody UnionCuidadorPacienteInDto unionCuidadorPacienteInDto) {
        CuidadorPaciente cuidadorPaciente = this.cuidadorPacienteService.findCuidadorActivo(unionCuidadorPacienteInDto.getPacienteInDto().getCedula());


        if (cuidadorPaciente != null) {
            this.cuidadorPacienteService.inactivarCuidador(cuidadorPaciente.getIdCuidadorPaciente());

        }
        CuidadorPacienteInDto cuidadorPacienteInDto = new CuidadorPacienteInDto();
        cuidadorPacienteInDto.setCuidador(unionCuidadorPacienteInDto.getCuidadorInDto().getCedulaCuidador());
        cuidadorPacienteInDto.setPaciente(unionCuidadorPacienteInDto.getPacienteInDto().getCedula());
        LocalDate fecha_ini = LocalDate.now();
        cuidadorPacienteInDto.setFecha_ini(fecha_ini);
        this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorPacienteInDto);
    }

    @PatchMapping("/cuidador/ReactivarCuidadorAntiguoSinActivo")
    public void ReactivarCuidadorAntiguoSinActivo(@RequestBody CuidadorInDto cuidador) {
        CuidadorPacienteInDto cuidadorPacienteInDto = new CuidadorPacienteInDto();
        cuidadorPacienteInDto.setCuidador(cuidador.getCedulaCuidador());
        cuidadorPacienteInDto.setPaciente(cuidadorPacienteInDto.getCuidador());
        LocalDate fecha_ini = LocalDate.now();
        cuidadorPacienteInDto.setFecha_ini(fecha_ini);
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
        LocalDate fecha_ini = LocalDate.now();
        cuidadorPacienteInDto.setFecha_ini(fecha_ini);
        this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorPacienteInDto);
    }


    @PatchMapping("/cuidador/inhabilitarCuidadorActivo")
    public void InhabilitarCuidadorActivo(@RequestBody UnionCuidadorPacienteInDto unionCuidadorPacienteInDto) {
        CuidadorPaciente cuidadorPaciente = this.cuidadorPacienteService.findCuidadorActivo(unionCuidadorPacienteInDto.getPacienteInDto().getCedula());

        if (cuidadorPaciente != null) {
            this.cuidadorPacienteService.inactivarCuidador(cuidadorPaciente.getIdCuidadorPaciente());
            cuidadorPaciente.setActivo(false);

        }
    }

    @PatchMapping("/cuidador/actualizar")
    public void actualizarCuidador(@RequestBody CuidadorInDto cuidadorInDto) {
        Cuidador cuidador = this.cuidadorService.actualizarCuidador(cuidadorInDto.getCedulaCuidador(), cuidadorInDto);
    }

    @PostMapping("/alergia/crear/{cedula}")
    public void crearAlergia(@PathVariable("cedula") String cedula, @RequestBody AlergiaInDto alergiaInDto){
        Alergia alergia= this.alergiaService.crearAlergia(alergiaInDto);

        agregarAlergiaByPaciente(cedula, alergia.getIdAlergia());
    }

    @PostMapping("/alergia/agregar/{cedula}/{id_alergia}")
    public void agregarAlergiaByPaciente(@PathVariable("cedula")String cedula, @PathVariable("id_alergia")int id_alergia){
        PacienteAlergiaInDto pacienteAlergiaInDto = new PacienteAlergiaInDto();
        pacienteAlergiaInDto.setAlergia(id_alergia);
        pacienteAlergiaInDto.setPaciente(cedula);
        this.pacienteAlergiaService.crearPacienteAlergia(pacienteAlergiaInDto);
    }
    @GetMapping("/alergia/listByPaciente/{cedula}")
    public List<PacienteAlergia> listarAlergiasPorPaciente(@PathVariable("cedula")long cedula){
        return this.pacienteAlergiaService.findAllByPaciente(cedula);
    }

    @PatchMapping("/alergia/editar/{id_alergia}")
    public void editarAlergia(@PathVariable("id_alergia") int idAlergia, @RequestBody AlergiaInDto alergiaInDto){
        Alergia alergia= this.alergiaService.actualizarAlergia(idAlergia, alergiaInDto);

    }

    @PatchMapping("/alergia/inactivar/{cedula}/{id_alergia}")
    public void inactivarAlergia(@PathVariable("cedula")long cedula,@PathVariable("id_alergia")int id_alergia) {
        this.pacienteAlergiaService.inactivarAlergia(cedula,id_alergia);
    }
    public PacienteAlergia findPacienteAlergia(@PathVariable("cedula")long cedula,@PathVariable("id_alergia")int id_alergia){
        return this.pacienteAlergiaService.findAlergiaPorPaciente(cedula,id_alergia);
    }

    @PatchMapping("/alergia/activar/{cedula}{id_alergia}")
    public void activarAlergia(@PathVariable("cedula")long cedula,@PathVariable("id_alergia")int id_alergia) {
        this.pacienteAlergiaService.activarAlergia(cedula,id_alergia);
    }

    @GetMapping("/alergia/ListPasadas/{cedula}")
    public List<PacienteAlergia> findAlergiasPasadas(@PathVariable("cedula")long cedula){
        return this.pacienteAlergiaService.findAlergiasPasadas(cedula);
    }
    @GetMapping("/enfermedad/findPasadas/{cedula}")
    public List<Cormobilidad> findEnfermedadesPasadas(@PathVariable("cedula")long cedula){
        return this.cormobilidadService.findEnfermedadesPasadas(cedula);
    }
    @PostMapping("/enfermedad/crear/{cedula}")
    public void crearEnfermedad(@PathVariable("cedula")String cedula,@RequestBody EnfermedadInDto enfermedadInDto){
        Enfermedad enfermedad=this.enfermedadService.crearEnfermedad(enfermedadInDto);
        agregarEnfermedadByPaciente(cedula, enfermedad.getIdEnfermedad());
    }
    @PostMapping("/enfermedad/añadir/{cedula},{id_enfermedad}")
    public void agregarEnfermedadByPaciente(@PathVariable("cedula")String cedula,@PathVariable("id_enfermedad")int id_enfermedad){
        CormobilidadInDto cormobilidadInDto=new CormobilidadInDto();
        cormobilidadInDto.setEnfermedad(id_enfermedad);
        cormobilidadInDto.setPaciente(cedula);
        this.cormobilidadService.crearCormobilidad(cormobilidadInDto);
    }
    @PatchMapping("/enfermedad/eliminar/{id_cormobilidad}")
    public void eliminarEnfermedad(@PathVariable("id_cormobilidad")int id_cormobilidad){
        this.cormobilidadService.inactivarCormobilidad(id_cormobilidad);
    }
    @PatchMapping("/enfermedad/activar/{id_cormobilidad}")
    public void activarCormobilidad(@PathVariable("id_cormobilidad")int id_cormobilidad){
        this.cormobilidadService.activarCormobilidad(id_cormobilidad);
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
    @PostMapping("/cita/crearCita")
    public void crearCita(@RequestBody CitaInDto citaInDto){
        this.citaService.crearCita(citaInDto);
    }
    @GetMapping("/cita/listByPaciente/{cedula}")
    public List<Cita> findAllCitas(@PathVariable("cedula")String cedula){
        Paciente paciente=new Paciente(cedula);
        return this.citaService.findAllByPaciente(paciente);
    }
    @PatchMapping("/cita/actualizar/{id_cita}")
    public void actualizarCita(@PathVariable("id_cita")int id_cita,@RequestBody CitaInDto citaInDto){
        this.citaService.actualizarCita(id_cita, citaInDto);
    }
    @DeleteMapping("/cita/eliminar/{id_cita}")
    public void eliminarCita(@PathVariable("id_cita")int id_cita){
        this.citaService.deleteById(id_cita);
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
    @GetMapping("/medicamento/findMedicamentoByPaciente/{cedulapaciente}")
    public List<FormulaMedicamento> findFormulaByPaciente(@PathVariable("cedulapaciente")String paciente){
        return this.formulaMedicamentoService.findAllByPaciente(paciente);
    }

    @PostMapping("/Medicamento/crear")
    public FormulaMedicamento crearFormulaMedicamento(@RequestBody FormulaMedicamentoInDto formulaMedicamentoInDto) {
        return this.formulaMedicamentoService.crearFormulaMedicamento(formulaMedicamentoInDto);
    }

    @PostMapping("/cita/formula/ProgramarMedicamento")
    public List<TomaMedicamento> programarMedicamento(@RequestBody ProgramarMedicamentoInDto programarMedicamentoInDto){
        ProgramarMedicamento programarMedicamento=this.programarMedicamentoService.crearProgramarMedicamento(programarMedicamentoInDto);
        List<TomaMedicamento> tomaMedicamentoList=this.tomaMedicamentoService.crearTomas(programarMedicamento,this.formulaMedicamentoService.findById(programarMedicamento.getFormulaMedicamento().getIdFormulaMedicamento()));
        return tomaMedicamentoList;
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
        this.viaAdministracionService.UpdateViaAdministracion(id,viaAdministracionInDto);
    }
    @DeleteMapping("/medicamento/eliminar/{id_medicamento}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable("id_medicamento") int id_medicamento) {
        this.formulaMedicamentoService.deleteById(id_medicamento);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/prescripcion")
    public ResponseEntity<List<Cita>> getPrescisciones(){
        List<Cita> citas=this.citaService.findAllCitas();
        if(citas==null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(citas);
    }
    @PostMapping("/prescripcion/prescripcionActual")
    public ResponseEntity<UnionCitaPrescripcionDias> getPresciscionActual(@RequestBody Paciente paciente){
        Cita cita=this.citaService.findUltimaCita(paciente);
        if(cita==null)
            return ResponseEntity.noContent().build();
        else{
            UnionCitaPrescripcionDias citaPres=new UnionCitaPrescripcionDias();
            citaPres.setCita(cita);
            List<PrescripcionDia> prescripcionDias=this.prescripcionDiaService.findByCita(cita);
            List<UnionPrescripcionDiasRecambios> listPrescripcionDiasRecambios=new ArrayList<>();
            for (PrescripcionDia prescripcionDia:prescripcionDias) {
                UnionPrescripcionDiasRecambios prescripcionDiasRecambios=new UnionPrescripcionDiasRecambios();
                prescripcionDiasRecambios.setPrescripcionDia(prescripcionDia);
                prescripcionDiasRecambios.setRecambios(this.recambioService.findByPrescripcionDia(prescripcionDia));
                listPrescripcionDiasRecambios.add(prescripcionDiasRecambios);
            }

            citaPres.setUnionPrescripcionDiasRecambios(listPrescripcionDiasRecambios);
            return ResponseEntity.ok(citaPres);
        }
    }

}


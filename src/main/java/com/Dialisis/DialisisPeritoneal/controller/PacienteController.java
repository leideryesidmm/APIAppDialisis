package com.Dialisis.DialisisPeritoneal.controller;

import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.persistence.repository.PacienteRepository;
import com.Dialisis.DialisisPeritoneal.persistence.repository.UsuarioRepository;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.*;
import com.Dialisis.DialisisPeritoneal.service.dto.Uniones.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final PacienteService pacienteService;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
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
    private final RecambioService recambioService;
    private final PrescripcionDiaService prescripcionDiaService;
    private final RecambioHechoService recambioHechoService;
    private final VisitaEspecialistaService visitaEspecialistaService;
    private final ChequeoMensualService chequeoMensualService;
    //private final AlimentacionPacienteService alimentacionPacienteService;


    public PacienteController(PacienteService pacienteService, VisitaEspecialistaService visitaEspecialistaService, ChequeoMensualService chequeoMensualService, UsuarioService usuarioService, UsuarioRepository usuarioRepository, RecambioService recambioService, MedicamentoService medicamentoService, CuidadorPacienteService cuidadorPacienteService, CuidadorService cuidadorService, EnfermedadService enfermedadService, CormobilidadService cormobilidadService, CitaService citaService, FormulaMedicamentoService formulaMedicamentoService, ProgramarMedicamentoService programarMedicamentoService, TomaMedicamentoService tomaMedicamentoService, PacienteAlergiaService pacienteAlergiaService, AlergiaService alergiaService, ViaAdministracionService viaAdministracionService, PrescripcionDiaService prescripcionDiaService, RecambioHechoService recambioHechoService) {
        this.pacienteService = pacienteService;
        this.usuarioService=usuarioService;
        this.usuarioRepository=usuarioRepository;
        this.visitaEspecialistaService=visitaEspecialistaService;
        this.chequeoMensualService=chequeoMensualService;
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
        this.recambioService=recambioService;
        this.prescripcionDiaService = prescripcionDiaService;
        this.recambioHechoService = recambioHechoService;
    }

    @PostMapping("/crearPaciente")
    public Paciente crearPaciente(@RequestBody PacienteInDto pacienteInDto){
        System.out.println(pacienteInDto);
        return this.pacienteService.crearPaciente(pacienteInDto);
    }

    @PostMapping("/findPacienteByCedula")
    public Paciente findPacienteByCedula(@RequestBody PacienteInDto pacienteInDto){
        System.out.println(pacienteInDto);
        System.out.println(this.pacienteService.findByCedula(pacienteInDto.getCedula()));
        return this.pacienteService.findByCedula(pacienteInDto.getCedula());
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

    @PatchMapping("/actualizar")
    public ResponseEntity<Void> actualizarDatosPaciente(@RequestBody PacienteInDto pacienteInDto){
        Paciente paciente= this.pacienteService.findByCedula(pacienteInDto.getCedula());


        this.pacienteService.actualizarDatosPaciente(pacienteInDto, paciente);
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

    /*@PostMapping("/foto")
    public ResponseEntity<String> uploadPhoto(@RequestBody UnionPacienteFotoInDto unionPacienteFotoInDto) {
        pacienteService.uploadPhoto(unionPacienteFotoInDto.getCedula(), unionPacienteFotoInDto.getFoto());
        System.out.println(unionPacienteFotoInDto);
        return ResponseEntity.ok("Imagen cargada exitosamente");
    }*/

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("cedula") String cedula,
                                              @RequestParam("foto") MultipartFile imageFile) {
        try {
            Usuario usuario = usuarioService.findAllBycedula(cedula);
            byte[] imageBytes = imageFile.getBytes();
            usuario.setFoto(imageBytes);

            // Actualiza otros campos del paciente si es necesario
            usuarioRepository.save(usuario);

            return ResponseEntity.ok("{\"success\": true}");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false}");
        }
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

    @PostMapping("/alergia/crear")
    public void crearAlergia(@RequestBody UnionPacienteAlergiaInDto unionPacienteAlergiaInDto){

        Alergia alergia= this.alergiaService.crearAlergia(unionPacienteAlergiaInDto.getAlergiaInDto());

        agregarAlergiaByPaciente(unionPacienteAlergiaInDto, alergia.getIdAlergia());
    }

    @PostMapping("/alergia/agregar/")
    public void agregarAlergiaByPaciente(@RequestBody UnionPacienteAlergiaInDto unionPacienteAlergiaInDto, int id){

        PacienteAlergiaInDto pacienteAlergiaInDto = new PacienteAlergiaInDto();
        pacienteAlergiaInDto.setAlergia(id);
        pacienteAlergiaInDto.setPaciente(unionPacienteAlergiaInDto.getPacienteInDto().getCedula());
        this.pacienteAlergiaService.crearPacienteAlergia(pacienteAlergiaInDto);
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
        Alergia alergia= this.alergiaService.actualizarAlergia(idAlergia, alergiaInDto);

    }

    @PatchMapping("/alergia/inactivar")
    public void inactivarAlergia(@RequestBody UnionPacienteAlergiaInDto unionPacienteAlergiaInDto) {
        this.pacienteAlergiaService.inactivarAlergia(unionPacienteAlergiaInDto.getPacienteInDto().getCedula(),unionPacienteAlergiaInDto.getAlergiaInDto().getIdAlergia());
    }
    public PacienteAlergia findPacienteAlergia(@PathVariable("cedula")Long cedula,@PathVariable("id_alergia")int id_alergia){
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

    @DeleteMapping("/prescripcionDia/eliminar/{id_prescripcionDia}")
    public void eliminarPrescripcionDia(@PathVariable("id_prescripcionDia")int id_prescripcionDia){
        this.prescripcionDiaService.deleteById(id_prescripcionDia);
    }
    @DeleteMapping("/recambio/eliminar/{id_recambio}")
    public void eliminarRecambio(@PathVariable("id_recambio")int id_recambio){
        this.recambioService.deleteById(id_recambio);
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
    @PostMapping("/medicamento/findMedicamentoByPaciente")
    public List<FormulaMedicamento> findFormulaByPaciente(@RequestBody PacienteInDto pacienteInDto){
        return this.formulaMedicamentoService.findAllByPaciente(pacienteInDto.getCedula());
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
    @PostMapping("/prescripcionesByPaciente")
    public ResponseEntity<List<UnionCitaPrescripcionDias>> getPresciscionesByPaciente(@RequestBody Paciente paciente){
        List<Cita> citas=this.citaService.findAllByPaciente(paciente);

        List<UnionCitaPrescripcionDias> prescripciones=new ArrayList<>();
        if(citas==null)
            return ResponseEntity.noContent().build();
        else {
            for (Cita cita:citas) {
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
    public ResponseEntity<UnionCitaPrescripcionDias> getPresciscionActual(@RequestBody Paciente paciente){
        //System.out.println(paciente);
        Cita cita=this.citaService.findUltimaCita(paciente);
        if(cita==null)
            return ResponseEntity.noContent().build();
        else{
            Paciente p=cita.getPaciente();
            p.setFoto(null);
            cita.setPaciente(p);
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

    @PostMapping("/recambio/crearRecambioHecho")
    public ResponseEntity<RecambioHecho>  crearRecambioHecho(@RequestBody RecambioHechoInDto recambioHechoInDto){
        try{
            RecambioHecho recambioHecho=this.recambioHechoService.crearRecambio(recambioHechoInDto);
            return ResponseEntity.ok(recambioHecho);
        }catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/recambio/editarRecambioHecho/{id_recambioHecho}")
    public ResponseEntity<RecambioHecho>  editarRecambioHecho(@RequestBody RecambioHechoInDto recambioHechoInDto,@PathVariable("id_recambioHecho") int id_recambioHecho){
        try{

            System.out.println(recambioHechoInDto);

            System.out.println(id_recambioHecho);
            RecambioHecho recambioHecho=this.recambioHechoService.editarRecambio(recambioHechoInDto,id_recambioHecho);
            return ResponseEntity.ok(recambioHecho);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/recambio/findRecambioHechoByPaciente")
    public ResponseEntity<List<RecambioHecho>>  findRecambioHechoByPaciente(@RequestBody Paciente paciente){
        try{
            List<RecambioHecho> recambioHechos=new ArrayList<>();
            ResponseEntity<UnionCitaPrescripcionDias> unionCitaPrescripcionDias=getPresciscionActual(paciente);
            UnionCitaPrescripcionDias presciscionActual=unionCitaPrescripcionDias.getBody();
            for(UnionPrescripcionDiasRecambios prescripcionDia :presciscionActual.getUnionPrescripcionDiasRecambios()) {
                for(Recambio recambio:prescripcionDia.getRecambios()){
                    //System.out.println(paciente);
                    List<RecambioHecho> recambioHechoLista2=this.recambioHechoService.findByRecambio(recambio);
                    //System.out.println(recambioHechoLista2);
                    recambioHechos.addAll(recambioHechoLista2);
                    //System.out.println("si");
                }
            }
            return ResponseEntity.ok(recambioHechos);//recambioHecho);
        }catch (Exception e){
            //System.out.println(e);
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
            System.out.println(recambioHechos);
            return ResponseEntity.ok(recambioHechos);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @PostMapping("/prescripcion/crearRecambio")
    public void crearRecambio(@RequestBody RecambioInDto recambioInDto){
        System.out.println(recambioInDto);
        Recambio recambio=new Recambio();
        recambio.setPrescripcionDia(this.prescripcionDiaService.findById(recambioInDto.getPrescripcionDia()));
        this.recambioService.crearRecambio(recambioInDto, recambio);
    }

    @PostMapping("/prescripcion/findRecambioHechoById/{id_recambio_hecho}")
    public ResponseEntity<RecambioHecho> findRecambioHechoById(@PathVariable int id_recambio_hecho){
        try{
            RecambioHecho recambio=this.recambioHechoService.findRecambioById(id_recambio_hecho);
            return ResponseEntity.ok(recambio);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/ultimaCita")
    public ResponseEntity<Cita> ultimaCita(@RequestBody Paciente paciente){
        System.out.println(paciente);
        Cita cita=this.citaService.findUltimaCita(paciente);
        return ResponseEntity.ok(cita);
    }

    @PostMapping("crear/prescripcionDia")
    public PrescripcionDia crearPrescripcionDia(@RequestBody PrescripcionDiaInDto prescripcionDiaInDto){

        return this.prescripcionDiaService.crearPrescripcionDia(prescripcionDiaInDto);
    }

    @PostMapping("/prescripcionDia/findByCita/{cita}")
    public List<PrescripcionDia> findprescripcionByCita(@PathVariable int cita){

        return this.prescripcionDiaService.findByCita(new Cita(cita));
    }

    @PostMapping("/recambio/findRecambioByPrescripcion/{prescripcionDia}")
    public List<Recambio> findRecambiosByPrescripcion(@PathVariable int prescripcionDia){
        return  this.recambioService.findByPrescripcionDia(new PrescripcionDia(prescripcionDia));
    }

    @PatchMapping("ActualizarVisita/{idVisita}")
    public ResponseEntity<Void> actualizarVisita(@PathVariable("idVisita")int idVisita,
                                                             @RequestBody VisitaEspecialistaInDto visitaEspecialistaInDto){
        this.visitaEspecialistaService.actualizarVisita(idVisita,visitaEspecialistaInDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("ActualizarChequeo/{id_chequeo_mensual}")
    public ResponseEntity<Void> actualizarChequeo(@PathVariable("id_chequeo_mensual")int id_chequeo_mensual,
                                                 @RequestBody ChequeoMensualInDto chequeoMensualInDto){
        System.out.println();
        this.chequeoMensualService.actualizarChequeo(id_chequeo_mensual,chequeoMensualInDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAllRecambiosHechos/{idCita}")
    public ResponseEntity<List<RecambioHecho>> findAllReacambioHechoByCita(@PathVariable("idCita") int idCita) {
        try {
            List<RecambioHecho> recambioHechos=new ArrayList<>();
            List<Recambio> recambioPrescri;
            Cita cita = new Cita(idCita);
            List<PrescripcionDia> prescripcionDias = this.prescripcionDiaService.findByCita(cita);
            List<UnionPrescripcionDiasRecambios> listPrescripcionDiasRecambios = new ArrayList<>();
            for (PrescripcionDia prescripcionDia : prescripcionDias) {
                UnionPrescripcionDiasRecambios prescripcionDiasRecambios = new UnionPrescripcionDiasRecambios();
                prescripcionDiasRecambios.setPrescripcionDia(prescripcionDia);
                prescripcionDiasRecambios.setRecambios(this.recambioService.findByPrescripcionDia(prescripcionDia));
                recambioPrescri=prescripcionDiasRecambios.getRecambios();
                for (Recambio recambio:recambioPrescri) {
                    recambioHechos.addAll(this.recambioHechoService.findByRecambio(recambio));
                }
            }

            return ResponseEntity.ok(recambioHechos);//recambioHecho);
        } catch (Exception e) {
            //System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PatchMapping("/finalizarPrescripcion/{idCita}")
    public ResponseEntity<Void>  finalizarPrescripcion(@PathVariable("idCita") int idCita) {
        try {
            this.citaService.finalizarById(idCita);
            return ResponseEntity.noContent().build();//recambioHecho);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}

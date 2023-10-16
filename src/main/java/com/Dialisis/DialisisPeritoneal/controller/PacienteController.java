package com.Dialisis.DialisisPeritoneal.controller;

import com.Dialisis.DialisisPeritoneal.persistence.entity.*;
import com.Dialisis.DialisisPeritoneal.persistence.repository.UsuarioRepository;
import com.Dialisis.DialisisPeritoneal.service.*;
import com.Dialisis.DialisisPeritoneal.service.dto.*;
import com.Dialisis.DialisisPeritoneal.service.dto.Uniones.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final PacienteService pacienteService;
    private final ParentescoService parentescoService;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final CuidadorPacienteService cuidadorPacienteService;
    private final CuidadorService cuidadorService;
    private final CitaService citaService;
    private final EpsService epsService;
    private final FormulaMedicamentoService formulaMedicamentoService;
    private final PacienteAlergiaService pacienteAlergiaService;
    private final AlergiaService alergiaService;
    private final ViaAdministracionService viaAdministracionService;
    private final RecambioService recambioService;
    private final PrescripcionDiaService prescripcionDiaService;
    private final RecambioHechoService recambioHechoService;
    private final VisitaEspecialistaService visitaEspecialistaService;
    private final ChequeoMensualService chequeoMensualService;


    public PacienteController(PacienteService pacienteService, ParentescoService parentescoService, VisitaEspecialistaService visitaEspecialistaService, ChequeoMensualService chequeoMensualService, UsuarioService usuarioService, UsuarioRepository usuarioRepository, RecambioService recambioService, CuidadorPacienteService cuidadorPacienteService, CuidadorService cuidadorService, CitaService citaService, EpsService epsService, FormulaMedicamentoService formulaMedicamentoService, PacienteAlergiaService pacienteAlergiaService, AlergiaService alergiaService, ViaAdministracionService viaAdministracionService, PrescripcionDiaService prescripcionDiaService, RecambioHechoService recambioHechoService) {
        this.pacienteService = pacienteService;
        this.parentescoService = parentescoService;
        this.usuarioService=usuarioService;
        this.usuarioRepository=usuarioRepository;
        this.visitaEspecialistaService=visitaEspecialistaService;
        this.chequeoMensualService=chequeoMensualService;
        this.cuidadorPacienteService = cuidadorPacienteService;
        this.cuidadorService = cuidadorService;
        this.citaService = citaService;
        this.epsService = epsService;
        this.formulaMedicamentoService = formulaMedicamentoService;
        this.pacienteAlergiaService = pacienteAlergiaService;
        this.alergiaService = alergiaService;
        this.viaAdministracionService = viaAdministracionService;
        this.recambioService=recambioService;
        this.prescripcionDiaService = prescripcionDiaService;
        this.recambioHechoService = recambioHechoService;
    }

    @PostMapping("/crearPaciente")
    public Paciente crearPaciente(@RequestBody PacienteInDto pacienteInDto){
        return this.pacienteService.crearPaciente(pacienteInDto);
    }

    @PostMapping("/findPacienteByCedula")
    public Paciente findPacienteByCedula(@RequestBody PacienteInDto pacienteInDto){
        return this.pacienteService.findByCedula(pacienteInDto.getCedula());
    }






    @PatchMapping("/actualizar")
    public ResponseEntity<Void> actualizarDatosPaciente(@RequestBody PacienteInDto pacienteInDto){
        Paciente paciente= this.pacienteService.findByCedula(pacienteInDto.getCedula());
        this.pacienteService.actualizarDatosPaciente(pacienteInDto, paciente);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("formulaMedicamento/actualizar/{id_formula_medicamento}")
    public ResponseEntity<Void> actualizarFormulaMedicamento(@PathVariable("id_formula_medicamento")int idFormulaMedicamento,
                                                             @RequestBody FormulaMedicamentoInDto formulaMedicamentoInDto){
        this.formulaMedicamentoService.actualizarFormula(idFormulaMedicamento,formulaMedicamentoInDto);
        return ResponseEntity.noContent().build();
    }

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
        cuidadorPacienteInDto.setFecha_ini(fechaIni);
        this.cuidadorPacienteService.crearCuidadorPaciente(cuidadorPacienteInDto);
    }

    @PatchMapping("/cuidador/ReactivarCuidadorAntiguoSinActivo")
    public void reactivarCuidadorAntiguoSinActivo(@RequestBody CuidadorInDto cuidador) {
        CuidadorPacienteInDto cuidadorPacienteInDto = new CuidadorPacienteInDto();
        cuidadorPacienteInDto.setCuidador(cuidador.getCedulaCuidador());
        cuidadorPacienteInDto.setPaciente(cuidadorPacienteInDto.getCuidador());
        LocalDate fechaIni = LocalDate.now();
        cuidadorPacienteInDto.setFecha_ini(fechaIni);
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
        cuidadorPacienteInDto.setFecha_ini(fechaIni);
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
        this.alergiaService.actualizarAlergia(idAlergia, alergiaInDto);

    }

    @PatchMapping("/alergia/inactivar")
    public void inactivarAlergia(@RequestBody UnionPacienteAlergiaInDto unionPacienteAlergiaInDto) {
        this.pacienteAlergiaService.inactivarAlergia(unionPacienteAlergiaInDto.getPacienteInDto().getCedula(),unionPacienteAlergiaInDto.getAlergiaInDto().getIdAlergia());
    }
    public PacienteAlergia findPacienteAlergia(@PathVariable("cedula")Long cedula,@PathVariable("id_alergia")int idAlergia){
        return this.pacienteAlergiaService.findAlergiaPorPaciente(cedula,idAlergia);
    }

    @PatchMapping("/alergia/activar/{cedula}{id_alergia}")
    public void activarAlergia(@PathVariable("cedula")long cedula,@PathVariable("id_alergia")int idAlergia) {
        this.pacienteAlergiaService.activarAlergia(cedula,idAlergia);
    }

    @GetMapping("/alergia/ListPasadas/{cedula}")
    public List<PacienteAlergia> findAlergiasPasadas(@PathVariable("cedula")long cedula){
        return this.pacienteAlergiaService.findAlergiasPasadas(cedula);
    }
    @GetMapping("/cita/listByPaciente/{cedula}")
    public List<Cita> findAllCitas(@PathVariable("cedula")String cedula){
        Paciente paciente=new Paciente(cedula);
        return this.citaService.findAllByPaciente(paciente);
    }
    @PatchMapping("/cita/actualizar/{id_cita}")
    public void actualizarCita(@PathVariable("id_cita")int idCita,@RequestBody CitaInDto citaInDto){
        this.citaService.actualizarCita(idCita, citaInDto);
    }
    @DeleteMapping("/cita/eliminar/{id_cita}")
    public void eliminarCita(@PathVariable("id_cita")int idCita){
        this.citaService.deleteById(idCita);
    }

    @DeleteMapping("/prescripcionDia/eliminar/{id_prescripcionDia}")
    public void eliminarPrescripcionDia(@PathVariable("id_prescripcionDia")int idPrescripcionDia){
        this.prescripcionDiaService.deleteById(idPrescripcionDia);
    }
    @DeleteMapping("/recambio/eliminar/{id_recambio}")
    public void eliminarRecambio(@PathVariable("id_recambio")int idRecambio){
        this.recambioService.deleteById(idRecambio);
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
    public ResponseEntity<Void> deleteMedicamento(@PathVariable("id_medicamento") int idMedicamento) {
        this.formulaMedicamentoService.deleteById(idMedicamento);
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
    public ResponseEntity<UnionCitaPrescripcionDias> getPresciscionActual(@RequestBody Paciente paciente){
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
    public ResponseEntity<RecambioHecho>  editarRecambioHecho(@RequestBody RecambioHechoInDto recambioHechoInDto,@PathVariable("id_recambioHecho") int idRecambioHecho){
        try{
            RecambioHecho recambioHecho=this.recambioHechoService.editarRecambio(recambioHechoInDto,idRecambioHecho);
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
                    List<RecambioHecho> recambioHechoLista2=this.recambioHechoService.findByRecambio(recambio);
                    recambioHechos.addAll(recambioHechoLista2);
                }
            }
            return ResponseEntity.ok(recambioHechos);
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




    @PostMapping("/prescripcion/crearRecambio")
    public void crearRecambio(@RequestBody RecambioInDto recambioInDto){
        Recambio recambio=new Recambio();
        recambio.setPrescripcionDia(this.prescripcionDiaService.findById(recambioInDto.getPrescripcionDia()));
        this.recambioService.crearRecambio(recambioInDto, recambio);
    }

    @PostMapping("/prescripcion/findRecambioHechoById/{id_recambio_hecho}")
    public ResponseEntity<RecambioHecho> findRecambioHechoById(@PathVariable int idRecambioHecho){
        try{
            RecambioHecho recambio=this.recambioHechoService.findRecambioById(idRecambioHecho);
            return ResponseEntity.ok(recambio);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/ultimaCita")
    public ResponseEntity<Cita> ultimaCita(@RequestBody Paciente paciente){
        Cita cita=this.citaService.findUltimaCita(paciente);
        if(cita!=null)
            return ResponseEntity.ok(cita);
        else{
            return ResponseEntity.noContent().build();
        }

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
    public ResponseEntity<Void> actualizarChequeo(@PathVariable("id_chequeo_mensual")int idChequeoMensual,
                                                 @RequestBody ChequeoMensualInDto chequeoMensualInDto){
        this.chequeoMensualService.actualizarChequeo(idChequeoMensual,chequeoMensualInDto);
        return ResponseEntity.noContent().build();
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

    @PatchMapping("/finalizarPrescripcion/{idCita}")
    public ResponseEntity<Void>  finalizarPrescripcion(@PathVariable("idCita") int idCita) {
        try {
            this.citaService.finalizarById(idCita);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/prescripcion/visitas")
    public ResponseEntity<List<VisitaEspecialista>> findAllVisitas(@RequestBody PacienteInDto pacienteInDto) {

        List<Cita> citas= citaService.findAllByPaciente(new Paciente(pacienteInDto.getCedula()));
        List<VisitaEspecialista> visitas = visitaEspecialistaService.findAllVisitas(citas);
            if(visitas!=null)
                return ResponseEntity.ok(visitas);
            else
                return ResponseEntity.noContent().build();
    }

    @PostMapping("/prescripcion/chequeos")
    public ResponseEntity<List<ChequeoMensual>> findAllChequeos(@RequestBody PacienteInDto pacienteInDto) {

        List<Cita> citas= citaService.findAllByPaciente(new Paciente(pacienteInDto.getCedula()));
        List<ChequeoMensual> chequeos = chequeoMensualService.findAllChequeos(citas);
        if(chequeos!=null)
            return ResponseEntity.ok(chequeos);
        else
            return ResponseEntity.noContent().build();
    }
    @GetMapping("/ListParentesco")
    public List<Parentesco> findAllParentesco() {
        return this.parentescoService.findAll();
    }
    @GetMapping("/ListEps")
    public List<Eps> findAllEps() {
        return this.epsService.findAllEps();
    }
    @PostMapping("/Cita")
    public Cita crearCita(@RequestBody CitaInDto citaInDto) {
        return this.citaService.crearCita(citaInDto);
    }
}

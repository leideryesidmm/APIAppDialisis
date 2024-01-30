package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.persistence.repository.*;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncryptionServiceBD {
    String b = "\u001B[0m"; //borrar
    String negro = "\033[30m";
    String rojo = "\033[31m";
    String verde = "\033[32m";
    String amarillo = "\033[33m";
    String azul = "\033[34m";
    String magenta = "\033[35m";
    String celeste = "\033[36m";
    String blanco = "\033[37m";

    String fRojo = "\033[41m";
    String fVerde = "\033[42m";
    String fAmarillo = "\033[43m";
    String fAzul = "\033[44m";
    String fMagenta = "\033[45m";
    String fCeleste = "\033[46m";
    String fGris = "\033[47m";
   private final EncryptionService encryptionService;
   private final AlergiaRepository alergiaRepository;
   private final PacienteRepository pacienteRepository;
   private final MedicoRepository medicoRepository;
   private final CitaRepository citaRepository;
   private final CuidadorRepository cuidadorRepository;
   private final CuidadorPacienteRepository cuidadorPacienteRepository;
   private final RecambioRepository recambioRepository;
   private final RecambioHechoRepository recambioHechoRepository;
   private final ChequeoMensualRepository chequeoMensualRepository;
   private final FormulaMedicamentoRepository formulaMedicamentoRepository;
   private final PacienteAlergiaRepository pacienteAlergiaRepository;
   private final UsuarioRepository usuarioRepository;

    public EncryptionServiceBD(EncryptionService encryptionService, AlergiaRepository alergiaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository, CitaRepository citaRepository, CuidadorRepository cuidadorRepository, CuidadorPacienteRepository cuidadorPacienteRepository, RecambioRepository recambioRepository, RecambioHechoRepository recambioHechoRepository, ChequeoMensualRepository chequeoMensualRepository, FormulaMedicamentoRepository formulaMedicamentoRepository, PacienteAlergiaRepository pacienteAlergiaRepository, UsuarioRepository usuarioRepository) {
        this.alergiaRepository = alergiaRepository;
        this.encryptionService = encryptionService;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.citaRepository = citaRepository;
        this.cuidadorRepository = cuidadorRepository;
        this.cuidadorPacienteRepository = cuidadorPacienteRepository;
        this.recambioRepository = recambioRepository;
        this.recambioHechoRepository = recambioHechoRepository;
        this.chequeoMensualRepository = chequeoMensualRepository;
        this.formulaMedicamentoRepository = formulaMedicamentoRepository;
        this.pacienteAlergiaRepository = pacienteAlergiaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Alergia> getAlergias(){
        List<Alergia> alergias=this.alergiaRepository.findAll();
        //this.alergiaRepository.deleteAll();
        return alergias;
    }
    public void encriptarAlergias(List<Alergia> alergias){
        for (Alergia alergia : alergias) {
            alergia=encryptionService.getEncFrontend().getAlergia().desencriptar(alergia);
            alergia=encryptionService.getEncBackend().getAlergia().encriptar(alergia);
            this.alergiaRepository.save(alergia);
        }
    }
    public List<Paciente> getPacientes(){
        try {
            List<Paciente> pacientes = this.pacienteRepository.findAll();
            //this.alergiaRepository.deleteAll();
            return pacientes;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void encriptarPacientes(List<Paciente> pacientes){
        try {
            for (Paciente paciente : pacientes) {
                paciente = encryptionService.getEncFrontend().getPaciente().desencriptar(paciente);
                paciente = encryptionService.getEncBackend().getPaciente().encriptar(paciente);
                this.pacienteRepository.save(paciente);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Medico> getMedicos(){
        List<Medico> medicos=this.medicoRepository.findAll();
        //this.alergiaRepository.deleteAll();
        return medicos;
    }
    public void encriptarMedicos(List<Medico> medicos){
        for (Medico medico : medicos) {
            medico=encryptionService.getEncFrontend().getMedico().desencriptar(medico);
            medico=encryptionService.getEncBackend().getMedico().encriptar(medico);
            this.medicoRepository.save(medico);
        }
    }
    public List<Cita> getCitas(){
        List<Cita> citas=this.citaRepository.findAll();
        //this.alergiaRepository.deleteAll();
        return citas;
    }
    public void encriptarCitas(List<Cita> citas){
        for (Cita cita : citas) {
            cita=encryptionService.getEncFrontend().getCita().desencriptar(cita);
            cita=encryptionService.getEncBackend().getCita().encriptar(cita);
            this.citaRepository.save(cita);
        }
    }
    public List<Cuidador> getCuidadores(){
        List<Cuidador> cuidador=this.cuidadorRepository.findAll();
        //this.alergiaRepository.deleteAll();
        return cuidador;
    }
    public void encriptarCuidador(List<Cuidador> cuidadores){
        for (Cuidador cuidador: cuidadores) {
            cuidador=encryptionService.getEncFrontend().getCuidador().desencriptar(cuidador);
            cuidador=encryptionService.getEncBackend().getCuidador().encriptar(cuidador);
            this.cuidadorRepository.save(cuidador);
        }
    }
    public List<CuidadorPaciente> getCuidadorPacientes(){
        List<CuidadorPaciente> cuidadorPacientes=this.cuidadorPacienteRepository.findAll();
        //this.alergiaRepository.deleteAll();
        return cuidadorPacientes;
    }
    public void encriptarCuidadorPacientes(List<CuidadorPaciente> cuidadorPacientes){
        for (CuidadorPaciente cuidadorPaciente: cuidadorPacientes) {
            cuidadorPaciente=encryptionService.getEncFrontend().getCuidadorPaciente().desencriptar(cuidadorPaciente);
            cuidadorPaciente=encryptionService.getEncBackend().getCuidadorPaciente().encriptar(cuidadorPaciente);
            this.cuidadorPacienteRepository.save(cuidadorPaciente);
        }
    }

    public List<Recambio> getRecambios(){
        List<Recambio> recambios=this.recambioRepository.findAll();
        //this.alergiaRepository.deleteAll();
        return recambios;
    }
    public void encriptarRecambios(List<Recambio> recambios){
        System.out.println(recambios);
        for (Recambio recambio: recambios) {
            recambio=encryptionService.getEncFrontend().getRecambio().desencriptar(recambio);
            System.out.println(recambio);
            recambio=encryptionService.getEncBackend().getRecambio().encriptar(recambio);

            this.recambioRepository.save(recambio);
        }
    }
    public List<RecambioHecho> getRecambiosHechos(){
        List<RecambioHecho> recambiosHechos=this.recambioHechoRepository.findAll();
        //this.alergiaRepository.deleteAll();
        return recambiosHechos;
    }
    public void encriptarRecambiosHechos(List<RecambioHecho> recambiosHechos){
        for (RecambioHecho recambioHecho: recambiosHechos) {
            recambioHecho=encryptionService.getEncFrontend().getRecambioHecho().desencriptar(recambioHecho);
            recambioHecho=encryptionService.getEncBackend().getRecambioHecho().encriptar(recambioHecho);
            this.recambioHechoRepository.save(recambioHecho);
        }
    }
    public List<ChequeoMensual> getChequeoMensual(){
        List<ChequeoMensual> chequeosMensuales=this.chequeoMensualRepository.findAll();
        return chequeosMensuales;
    }
    public void encriptarChequeosMensuales(List<ChequeoMensual> chequeosMensuales){
        for (ChequeoMensual chequeoMensual: chequeosMensuales) {
            chequeoMensual=encryptionService.getEncFrontend().getChequeo().desencriptar(chequeoMensual);
            chequeoMensual=encryptionService.getEncBackend().getChequeo().encriptar(chequeoMensual);
            this.chequeoMensualRepository.save(chequeoMensual);
        }
    }
    public List<FormulaMedicamento> getFormulasMedicamentos(){
        List<FormulaMedicamento> formulasMedicamentos=this.formulaMedicamentoRepository.findAll();
        return formulasMedicamentos;
    }
    public void encriptarFormulasMedicamentos(List<FormulaMedicamento> formulasMedicamentos){
        for (FormulaMedicamento formulaMedicamento: formulasMedicamentos) {
            formulaMedicamento=encryptionService.getEncFrontend().getFormulaMedicamento().desencriptar(formulaMedicamento);
            formulaMedicamento=encryptionService.getEncBackend().getFormulaMedicamento().encriptar(formulaMedicamento);
            this.formulaMedicamentoRepository.save(formulaMedicamento);
        }
    }
    public List<PacienteAlergia> getPacientesAlergias(){
        List<PacienteAlergia> pacienteAlergias=this.pacienteAlergiaRepository.findAll();
        //this.alergiaRepository.deleteAll();
        return pacienteAlergias;
    }
    public void encriptarPacientesAlergias(List<PacienteAlergia> pacienteAlergias){
        try{
        for (PacienteAlergia pacienteAlergia: pacienteAlergias) {
            pacienteAlergia=encryptionService.getEncFrontend().getPacienteAlergia().desencriptar(pacienteAlergia);
            pacienteAlergia=encryptionService.getEncBackend().getPacienteAlergia().encriptar(pacienteAlergia);
            this.pacienteAlergiaRepository.save(pacienteAlergia);
        }}catch (Exception e){
            e.printStackTrace();
        }
    }
    public void desencriptarPacientew(String p){
        try{p="yY71y94RdYyIa3l50BrXtA==";
            Paciente paciente =new Paciente("stghZ3dtHCy8WyefwX2grw==");
            paciente=encryptionService.getEncBackend().getPaciente().desencriptar(paciente);
            System.out.println(paciente);
            }catch (Exception e){
            e.printStackTrace();
        }

    }
    public List<Usuario> getAdmins(){
        List<Usuario> admins=this.usuarioRepository.findBytipoUsuario("admin");
        //this.alergiaRepository.deleteAll();
        return admins;
    }
    public void encriptarAdmins(List<Usuario> admins){
        try{
            for (Usuario usuario: admins) {
                usuario=encryptionService.getEncFrontend().getAdmin().desencriptar(usuario);
                usuario=encryptionService.getEncBackend().getAdmin().encriptar(usuario);
                this.usuarioRepository.save(usuario);
            }}catch (Exception e){
            e.printStackTrace();
        }
    }


}

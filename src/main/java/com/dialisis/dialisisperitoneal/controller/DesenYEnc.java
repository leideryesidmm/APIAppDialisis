package com.dialisis.dialisisperitoneal.controller;

import com.dialisis.dialisisperitoneal.persistence.entity.*;
import com.dialisis.dialisisperitoneal.service.EncryptionServiceBD;
import com.dialisis.dialisisperitoneal.service.dto.UsuarioInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desencriptarBD")
public class DesenYEnc {
    private final EncryptionServiceBD encryptionServiceBD;

    public DesenYEnc(EncryptionServiceBD encryptionServiceBD) {
        this.encryptionServiceBD = encryptionServiceBD;
    }

    @GetMapping("/getAlergias")
    public List<Alergia> getAlergias(){
        return this.encryptionServiceBD.getAlergias();
    }

    @PostMapping("/encriptarAlergias")
    public void encriptarAlergias(@RequestBody List<Alergia> alergias){
        this.encryptionServiceBD.encriptarAlergias(alergias);
    }
    @GetMapping("/getPacientes")
    public List<Paciente> getPacientes(){
        return this.encryptionServiceBD.getPacientes();
    }

    @PostMapping("/encriptarPacientes")
    public void encriptarPacientes(@RequestBody List<Paciente> pacientes){
        this.encryptionServiceBD.encriptarPacientes(pacientes);
    }
    @GetMapping("/getMedicos")
    public List<Medico> getMedicos(){
        return this.encryptionServiceBD.getMedicos();
    }

    @PostMapping("/encriptarMedicos")
    public void encriptarMedicos(@RequestBody List<Medico> medicos){
        this.encryptionServiceBD.encriptarMedicos(medicos);
    }
    @GetMapping("/getCitas")
    public List<Cita> getCitas(){
        return this.encryptionServiceBD.getCitas();
    }

    @PostMapping("/encriptarCitas")
    public void encriptarCita(@RequestBody List<Cita> citas){
        this.encryptionServiceBD.encriptarCitas(citas);
    }
    @GetMapping("/getCuidadores")
    public List<Cuidador> getCuidadores(){
        return this.encryptionServiceBD.getCuidadores();
    }

    @PostMapping("/encriptarCuidadores")
    public void encriptarCuidadores(@RequestBody List<Cuidador> cuidador){

        this.encryptionServiceBD.encriptarCuidador(cuidador);
    }
    @GetMapping("/getCuidadorPacientes")
    public List<CuidadorPaciente> getCuidadorPacientes(){
        return this.encryptionServiceBD.getCuidadorPacientes();
    }

    @PostMapping("/encriptarCuidadorPacientes")
    public void encriptarCuidadorPacientes(@RequestBody List<CuidadorPaciente> cuidadorPacientes){
        this.encryptionServiceBD.encriptarCuidadorPacientes(cuidadorPacientes);
    }
    @GetMapping("/getRecambios")
    public List<Recambio> getRecambios(){
        return this.encryptionServiceBD.getRecambios();
    }

    @PostMapping("/encriptarRecambios")
    public void encriptarRecambios(@RequestBody List<Recambio> recambios){
        this.encryptionServiceBD.encriptarRecambios(recambios);
    }

    @GetMapping("/getRecambiosHechos")
    public List<RecambioHecho> getRecambiosHechos(){
        return this.encryptionServiceBD.getRecambiosHechos();
    }

    @PostMapping("/encriptarRecambiosHechos")
    public void encriptarRecambiosHechos(@RequestBody List<RecambioHecho> recambioHechos){
        this.encryptionServiceBD.encriptarRecambiosHechos(recambioHechos);
    }
    @GetMapping("/getChequeosMensuales")
    public List<ChequeoMensual> getChequeosMensuales(){
        return this.encryptionServiceBD.getChequeoMensual();
    }

    @PostMapping("/encriptarChequeosMensuales")
    public void encriptarChequeosMensuales(@RequestBody List<ChequeoMensual> chequeosMensuales){
        this.encryptionServiceBD.encriptarChequeosMensuales(chequeosMensuales);
    }
    @GetMapping("/getFormulasMedicamentos")
    public List<FormulaMedicamento> getFormulasMedicamentos(){
        return this.encryptionServiceBD.getFormulasMedicamentos();
    }

    @PostMapping("/encriptarFormulasMedicamentos")
    public void encriptarFormulasMedicamentos(@RequestBody List<FormulaMedicamento> formulasMedicamentos){
        this.encryptionServiceBD.encriptarFormulasMedicamentos(formulasMedicamentos);
    }
    @GetMapping("/getPacientesAlergias")
    public List<PacienteAlergia> getPacientesAlergias(){
        return this.encryptionServiceBD.getPacientesAlergias();
    }

    @PostMapping("/encriptarPacientesAlergias")
    public void encriptarPacientesAlergias(@RequestBody List<PacienteAlergia> pacienteAlergias){
        this.encryptionServiceBD.encriptarPacientesAlergias(pacienteAlergias);
    }
    @PostMapping("/pacientew")
    public void desencriptarPacientew(@RequestBody String p){
        this.encryptionServiceBD.desencriptarPacientew(p);
    }
    @GetMapping("/findAdmins")
    public ResponseEntity<List<Usuario>> getAdmins(){
        List<Usuario> admins= this.encryptionServiceBD.getAdmins();
        if(admins==null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(admins);
    }
    @PostMapping("/encriptarAdmins")
    public void encriptarAdmins(@RequestBody List<Usuario> usuarios){
        this.encryptionServiceBD.encriptarAdmins(usuarios);
    }

}

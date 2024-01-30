package com.dialisis.dialisisperitoneal.controller;

import com.dialisis.dialisisperitoneal.persistence.entity.Medico;
import com.dialisis.dialisisperitoneal.persistence.entity.Usuario;
import com.dialisis.dialisisperitoneal.service.MedicoService;
import com.dialisis.dialisisperitoneal.service.UsuarioService;
import com.dialisis.dialisisperitoneal.service.dto.MedicoInDto;
import com.dialisis.dialisisperitoneal.service.dto.UsuarioInDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final MedicoService medicoService;

    public UsuarioController(UsuarioService usuarioService, MedicoService medicoService) {
        this.usuarioService = usuarioService;
        this.medicoService = medicoService;
    }
    @PostMapping
    public Usuario crearoUpdateUsuario(@RequestBody UsuarioInDto usuarioInDto){
        return this.usuarioService.createoUpdateUsuario(usuarioInDto);
    }
    @GetMapping("/findAllUsuarios")
    public List<Usuario> findAllUsuarios(){
        return this.usuarioService.findAll();
    }
    @PostMapping("/cedula")
    public ResponseEntity<Usuario> findAllUsuarios(@RequestBody UsuarioInDto usuarioInDto) throws IOException {
        Usuario usuario=this.usuarioService.findAllBycedula(usuarioInDto.getCedula());
        if(usuario!=null)
        return ResponseEntity.ok(usuario);
        else {
        return ResponseEntity.status(204).build();
            }
    }
    @PatchMapping("/cambiarContrasenia")
    public ResponseEntity<Void> cambiarcontrasenia(@RequestBody UsuarioInDto usuarioInDto){
        this.usuarioService.cambiarContrasenia(usuarioInDto.getCedula(),usuarioInDto.getContrasenia());
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/cambioContraseniaPrimeraVez")
    public ResponseEntity<Void> cambiarcontraseniaPrimeraVez(@RequestBody UsuarioInDto usuarioInDto){
        if(usuarioInDto.getContrasenia()!="") {
            this.usuarioService.cambioContraseniaPrimeraVez(usuarioInDto.getCedula(), usuarioInDto.getContrasenia());
        }
        this.usuarioService.marcarCambiada(usuarioInDto.getCedula());
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/cambiar_celular/{cedula},{celular}")
    public ResponseEntity<Void> cambiarCelular(@PathVariable("cedula") String cedula,@PathVariable("celular") String celular) {
        this.usuarioService.cambiarCelular(cedula, celular);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/crearMedico")
    public Medico crearMedico(@RequestBody MedicoInDto medicoInDto){
        return this.medicoService.crearMedico(medicoInDto);
    }
    @PostMapping("/findMedicoByCedula")
    public ResponseEntity<Medico> findMedicoByCedula(@RequestBody MedicoInDto medicoInDto) {
        try {
            Medico medico=this.medicoService.findByCedula(medicoInDto.getCedula());
            if(medico!=null){
            return ResponseEntity.ok(medico);}
            else{
                return ResponseEntity.status(204).build();
            }
        }
        catch (Exception e) {
            e.getMessage();
            return ResponseEntity.noContent().build();
        }
    }
    @PatchMapping("/actualizarMedico")
    public ResponseEntity<Void> actualizarDatosMedico(@RequestBody Medico medicoInDto){
        try {

                this.medicoService.actualizarDatosMedico(medicoInDto);
                return ResponseEntity.noContent().build();



        }catch (Exception e) {
            e.printStackTrace();

             return ResponseEntity.status(500).build();
        }
    }
    @PatchMapping("/inhabilitarMedico")
    public void inhabilitarMedico(@RequestBody MedicoInDto medicoInDto) {
        this.usuarioService.inactivarUsuario(medicoInDto.getCedula());
    }
    @PatchMapping("/reactivarMedico")
    public void reactivarMedico(@RequestBody MedicoInDto medicoInDto) {
        this.usuarioService.activarUsuario(medicoInDto.getCedula());
    }
    @PatchMapping("/restaurarContrasenia")
    public void restaurarContrasenia(@RequestBody UsuarioInDto usuarioInDto){
        this.usuarioService.restaurarContrasenia(usuarioInDto.getCedula());
    }

    @PostMapping("/findAdmin")
    public ResponseEntity<Usuario> findAdmin(@RequestBody Usuario usuario){
       Usuario usu= this.usuarioService.findAdmin(usuario);
        if(usu==null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usu);
    }
}

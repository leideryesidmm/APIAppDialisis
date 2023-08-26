package com.Dialisis.DialisisPeritoneal.controller;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Usuario;
import com.Dialisis.DialisisPeritoneal.service.UsuarioService;
import com.Dialisis.DialisisPeritoneal.service.dto.UsuarioInDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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
        public Usuario findAllUsuarios(@RequestBody UsuarioInDto usuarioInDto) throws IOException {

        return this.usuarioService.findAllBycedula(usuarioInDto.getCedula());
    }

    @PatchMapping("/cambiarContrasenia")
    public ResponseEntity<Void> cambiarcontrasenia(@RequestBody UsuarioInDto usuarioInDto){

        this.usuarioService.cambiarcontraseña(usuarioInDto.getCedula(),usuarioInDto.getContrasenia());

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/cambioContraseniaPrimeraVez")
    public ResponseEntity<Void> cambiarcontraseniaPrimeraVez(@RequestBody UsuarioInDto usuarioInDto){
        System.out.println(usuarioInDto);
        if(usuarioInDto.getContrasenia()!="") {
            this.usuarioService.cambiocontraseñaPrimeraVez(usuarioInDto.getCedula(), usuarioInDto.getContrasenia());
        }
        this.usuarioService.marcarCambiada(usuarioInDto.getCedula());
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/cambiar_celular/{cedula},{celular}")
    public ResponseEntity<Void> cambiarCelular(@PathVariable("cedula") String cedula,@PathVariable("celular") String celular) {
        this.usuarioService.cambiarCelular(cedula, celular);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/imagen")
    public ResponseEntity<byte[]> getUsuarioFoto(@RequestBody UsuarioInDto usuarioInDto) {
        System.out.println(usuarioInDto);
        byte[] fotoBytes = usuarioService.getFotoByCedula(usuarioInDto.getCedula());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Cambia esto según el tipo de imagen

        return new ResponseEntity<>(fotoBytes, headers, HttpStatus.OK);
    }
}

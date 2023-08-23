package com.Dialisis.DialisisPeritoneal.controller;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Usuario;
import com.Dialisis.DialisisPeritoneal.service.UsuarioService;
import com.Dialisis.DialisisPeritoneal.service.dto.UsuarioInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("si llama");
        System.out.println(this.usuarioService.findAll());
        return this.usuarioService.findAll();
    }
    @GetMapping("/cedula/{cedula}")
    public Usuario findAllUsuarios(@PathVariable("cedula") long cedula){
        return this.usuarioService.findAllBycedula(cedula);
    }
    @PatchMapping("/cambiarContrasenia")
    public ResponseEntity<Void> cambiarcontrasenia(@RequestBody UsuarioInDto usuarioInDto){
        System.out.println(usuarioInDto);
        this.usuarioService.cambiarcontraseña(usuarioInDto.getCedula(),usuarioInDto.getContrasenia());
        return null;
    }
    @PatchMapping("/cambiar_celular/{cedula},{celular}")
    public ResponseEntity<Void> cambiarCelular(@PathVariable("cedula") String cedula,@PathVariable("celular") String celular) {
        this.usuarioService.cambiarCelular(cedula, celular);
        return ResponseEntity.noContent().build();
    }
}

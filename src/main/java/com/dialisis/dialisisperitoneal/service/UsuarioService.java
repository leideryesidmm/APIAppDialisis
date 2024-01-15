package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.UsuarioInDtoToUsuario;
import com.dialisis.dialisisperitoneal.persistence.entity.Usuario;
import com.dialisis.dialisisperitoneal.persistence.repository.UsuarioRepository;
import com.dialisis.dialisisperitoneal.service.dto.UsuarioInDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioInDtoToUsuario mapper;

    public UsuarioService(UsuarioRepository repository, UsuarioInDtoToUsuario mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public Usuario createoUpdateUsuario(UsuarioInDto usuario){
        Usuario usuario1=mapper.map(usuario);
        return this.repository.save(usuario1);
    }
    public List<Usuario> findAll(){

        return this.repository.findAll();
    }

    public Usuario findAllBycedula(String cedula){

        return this.repository.findAllBycedula(cedula);
    }

    public byte[] getFotoByCedula(String cedula) {
        Usuario usuario = repository.findAllBycedula(cedula);
        return usuario.getFoto();
    }
    @Transactional
    public void cambiarContrasenia(String cedula,String contrasenia){
        this.repository.cambiarContrasenia(cedula,contrasenia);
    }

    @Transactional
    public void cambioContraseniaPrimeraVez(String cedula,String contrasenia){
        this.repository.cambioContraseniaPrimeraVez(cedula,contrasenia);
    }

    @Transactional
    public void marcarCambiada(String cedula){
        this.repository.marcarCambiada(cedula);
    }

    @Transactional
    public void cambiarCelular(String cedula,String celular){
        this.repository.cambiarCelular(cedula,celular);
    }

    public List<Usuario> findAdmin(){
        return this.repository.findBytipoUsuario("admin");
    }

    @Transactional
    public void inactivarUsuario(String cedula) {
        this.repository.inactivarUsuario(cedula);
    }

    @Transactional
    public void activarUsuario(String cedula) {
        this.repository.activarUsuario(cedula);
    }

    @Transactional
    public void restaurarContrasenia(String cedula){
        this.repository.restaurarContrasenia(cedula);
    }
    public void saveUsuario(Usuario usuario){
        this.repository.save(usuario);
    }
}

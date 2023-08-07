package com.Dialisis.DialisisPeritoneal.service;

import com.Dialisis.DialisisPeritoneal.mapper.UsuarioInDtoToUsuario;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Usuario;
import com.Dialisis.DialisisPeritoneal.persistence.repository.UsuarioRepository;
import com.Dialisis.DialisisPeritoneal.service.dto.UsuarioInDto;
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

    public Usuario findAllBycedula(long cedula){

        return this.repository.findAllBycedula(cedula);
    }
    @Transactional
    public void cambiarcontraseña(String cedula,String contrasenia){
        this.repository.cambiarcontraseña(cedula,contrasenia);
    }
    @Transactional
    public void cambiarCelular(long cedula,long celular){
        this.repository.cambiarCelular(cedula,celular);
    }

}

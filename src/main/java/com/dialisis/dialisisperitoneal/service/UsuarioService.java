package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.UsuarioInDtoToUsuario;
import com.dialisis.dialisisperitoneal.persistence.entity.Usuario;
import com.dialisis.dialisisperitoneal.persistence.repository.UsuarioRepository;
import com.dialisis.dialisisperitoneal.service.encryption.EncryptionService;
import com.dialisis.dialisisperitoneal.service.dto.UsuarioInDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioInDtoToUsuario mapper;
    private final EncryptionService encryptionService;

    public UsuarioService(EncryptionService encryptionService, UsuarioRepository repository, UsuarioInDtoToUsuario mapper) {
        this.encryptionService = encryptionService;
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

    public Usuario findAdmin(Usuario usuarioAdmin){
        List<Usuario> usuarios=this.repository.findBytipoUsuario("admin");
        List<Usuario> usuariosAdmins= new ArrayList<>();
        if(!usuarios.isEmpty()) {
            for (Usuario usuario:
                 usuarios) {
                Usuario usAdmin=this.encryptionService.getEncBackend().getAdmin().desencriptar(usuario);

                usuariosAdmins.add(usAdmin);
            }

            Usuario userFrontend= this.encryptionService.getEncFrontend().getAdmin().desencriptar(usuarioAdmin);
            if(usuariosAdmins!=null) {

                for (Usuario usu :
                        usuariosAdmins) {
                    if (userFrontend.getCedula().equals(usu.getCedula()) && userFrontend.getContrasenia().equals(usu.getContrasenia())) {

                        Usuario usuarioEncriptado = this.encryptionService.getEncFrontend().getAdmin().encriptar(usu);
                        return usuarioEncriptado;
                    }
                }
            }
        }
        return null;
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

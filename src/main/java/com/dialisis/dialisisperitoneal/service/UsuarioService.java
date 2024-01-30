package com.dialisis.dialisisperitoneal.service;

import com.dialisis.dialisisperitoneal.mapper.UsuarioInDtoToUsuario;
import com.dialisis.dialisisperitoneal.persistence.entity.Medico;
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
        usuario1=encryptionService.getEncFrontend().getAdmin().desencriptar(usuario1);
        this.repository.save(encryptionService.getEncBackend().getAdmin().encriptar(usuario1));
        usuario1=encryptionService.getEncBackend().getAdmin().desencriptar(usuario1);
        return encryptionService.getEncFrontend().getAdmin().encriptar(usuario1);
    }
    public List<Usuario> findAll() {
        List<Usuario> usuarios = this.repository.findAll();
            for (int i = 0; i < usuarios.size(); i++){
                Usuario usuB = encryptionService.getEncBackend().getAdmin().desencriptar(usuarios.get(i));
                usuB=encryptionService.getEncFrontend().getAdmin().encriptar(usuB);
            usuarios.set(i, usuB);
        }
            return usuarios;
    }
    public Usuario findAllBycedula(String cedula){
        Usuario usu= new Usuario();
        usu.setCedula(cedula);
        usu= encryptionService.getEncFrontend().getAdmin().desencriptar(usu);
        usu= encryptionService.getEncBackend().getAdmin().encriptar(usu);
        Usuario usuarioBack= this.repository.findAllBycedula(usu.getCedula());
            usuarioBack= encryptionService.getEncBackend().getAdmin().desencriptar(usuarioBack);
            return encryptionService.getEncFrontend().getAdmin().encriptar(usuarioBack);

    }

    @Transactional
    public void cambiarContrasenia(String cedula,String contrasenia){
        Usuario u=new Usuario();
        u.setCedula(cedula);
        u.setContrasenia(contrasenia);
        u=encryptionService.getEncFrontend().getAdmin().desencriptar(u);
        u=encryptionService.getEncBackend().getAdmin().encriptar(u);
        this.repository.cambiarContrasenia(u.getCedula(),u.getContrasenia());
    }
    @Transactional
    public void cambioContraseniaPrimeraVez(String cedula,String contrasenia){
        Usuario u=new Usuario();
        u.setCedula(cedula);
        u.setContrasenia(contrasenia);
        u=encryptionService.getEncFrontend().getAdmin().desencriptar(u);
        u=encryptionService.getEncBackend().getAdmin().encriptar(u);
        this.repository.cambioContraseniaPrimeraVez(u.getCedula(),u.getContrasenia());
    }
    @Transactional
    public void marcarCambiada(String cedula){
        Usuario u=new Usuario();
        u.setCedula(cedula);
        u=encryptionService.getEncFrontend().getAdmin().desencriptar(u);
        u=encryptionService.getEncBackend().getAdmin().encriptar(u);
        this.repository.marcarCambiada(u.getCedula());
    }
    @Transactional
    public void cambiarCelular(String cedula,String celular){
        Usuario u=new Usuario();
        u.setCedula(cedula);
        u.setCelular(celular);
        u=encryptionService.getEncFrontend().getAdmin().desencriptar(u);
        u=encryptionService.getEncBackend().getAdmin().encriptar(u);
        this.repository.cambiarCelular(u.getCedula(),u.getCelular());
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
        Usuario u=new Usuario();
        u.setCedula(cedula);
        u=encryptionService.getEncFrontend().getAdmin().desencriptar(u);
        u=encryptionService.getEncBackend().getAdmin().encriptar(u);
        this.repository.inactivarUsuario(u.getCedula());
    }
    @Transactional
    public void activarUsuario(String cedula) {
        Usuario u=new Usuario();
        u.setCedula(cedula);
        u=encryptionService.getEncFrontend().getAdmin().desencriptar(u);
        u=encryptionService.getEncBackend().getAdmin().encriptar(u);
        this.repository.activarUsuario(u.getCedula());
    }

    @Transactional
    public void restaurarContrasenia(String cedula){
        Usuario u=new Usuario();
        u.setCedula(cedula);
        u=encryptionService.getEncFrontend().getAdmin().desencriptar(u);
        u=encryptionService.getEncBackend().getAdmin().encriptar(u);
        this.repository.restaurarContrasenia(u.getCedula());
    }
    public void saveUsuario(Usuario usuario){
        usuario=encryptionService.getEncFrontend().getAdmin().desencriptar(usuario);
        usuario=encryptionService.getEncBackend().getAdmin().encriptar(usuario);
        this.repository.save(usuario);
    }
}
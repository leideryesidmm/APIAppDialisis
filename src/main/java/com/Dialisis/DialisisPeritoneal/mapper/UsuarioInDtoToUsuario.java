package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Usuario;
import org.springframework.stereotype.Component;
import com.Dialisis.DialisisPeritoneal.service.dto.UsuarioInDto;

@Component
public class UsuarioInDtoToUsuario implements IMapper<UsuarioInDto, Usuario>{
    @Override
    public Usuario map(UsuarioInDto in) {
        Usuario usuario=new Usuario();
        usuario.setCedula(in.getCedula());
        usuario.setNombre(in.getNombre());
        usuario.setContrasenia(in.getContrasenia());
        usuario.setCelular(in.getCelular());
        usuario.setFoto(in.getFoto());
        return usuario;
    }
}

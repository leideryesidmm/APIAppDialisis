package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Usuario;
import org.springframework.stereotype.Component;
import com.dialisis.dialisisperitoneal.service.dto.UsuarioInDto;

@Component
public class UsuarioInDtoToUsuario implements IMapper<UsuarioInDto, Usuario>{
    @Override
    public Usuario map(UsuarioInDto in) {
        Usuario usuario=new Usuario();
        usuario.setCedula(in.getCedula());
        usuario.setNombre(in.getNombre());
        usuario.setContrasenia(in.getContrasenia());
        usuario.setCelular(in.getCelular());
        usuario.setCorreo(in.getCorreo());
        usuario.setActivo(in.isActivo());
        usuario.setTipoDocumento(in.getTipoDocumento());
        return usuario;
    }
}

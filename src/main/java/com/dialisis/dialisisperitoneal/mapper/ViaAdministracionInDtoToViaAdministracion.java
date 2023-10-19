package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.ViaAdministracion;
import com.dialisis.dialisisperitoneal.service.dto.ViaAdministracionInDto;
import org.springframework.stereotype.Component;

@Component
public class ViaAdministracionInDtoToViaAdministracion implements IMapper<ViaAdministracionInDto, ViaAdministracion> {

    @Override
    public ViaAdministracion map(ViaAdministracionInDto in) {
        ViaAdministracion viaAdministracion=new ViaAdministracion();
        viaAdministracion.setDescripcion(in.getDescripcion());
        return viaAdministracion;
    }
}

package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.ViaAdministracion;
import com.Dialisis.DialisisPeritoneal.service.dto.ViaAdministracionInDto;
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

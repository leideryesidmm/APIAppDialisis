package com.Dialisis.DialisisPeritoneal.mapper;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Parentesco;
import com.Dialisis.DialisisPeritoneal.service.dto.ParentescoInDto;
import org.springframework.stereotype.Component;

@Component
public class ParentescoInDtoToParentesco implements IMapper<ParentescoInDto, Parentesco> {

    public Parentesco map(ParentescoInDto in){
        Parentesco parentesco= new Parentesco();
        parentesco.setDescripcion(in.getDesripcion());
        return parentesco;
    }
}

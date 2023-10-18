package com.dialisis.dialisisperitoneal.mapper;

import com.dialisis.dialisisperitoneal.persistence.entity.Parentesco;
import com.dialisis.dialisisperitoneal.service.dto.ParentescoInDto;
import org.springframework.stereotype.Component;

@Component
public class ParentescoInDtoToParentesco implements IMapper<ParentescoInDto, Parentesco> {

    public Parentesco map(ParentescoInDto in){
        Parentesco parentesco= new Parentesco();
        parentesco.setDescripcion(in.getDesripcion());
        return parentesco;
    }
}

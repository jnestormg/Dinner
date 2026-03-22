package com.diner.diner.mappers;

import org.mapstruct.Mapper;

import com.diner.diner.controllers.dto.DireccionDTO;
import com.diner.diner.entities.Direccion;

@Mapper(componentModel = "spring")
public interface DireccionMapper {
    
    Direccion toEntity(DireccionDTO direccion);

    DireccionDTO toDTO(Direccion direccion);

}

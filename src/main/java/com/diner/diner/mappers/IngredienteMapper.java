package com.diner.diner.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.diner.diner.controllers.dto.IngredienteDTO;
import com.diner.diner.entities.Ingrediente;

@Mapper(componentModel = "spring")
public interface IngredienteMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "producto.id", source = "productoId")
    Ingrediente toEntity(IngredienteDTO ingredienteDTO);

    @Mapping(target = "productoId", source = "producto.id")
    IngredienteDTO toDTO(Ingrediente ingrediente);  

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "producto.id", source = "productoId")
    void updateEntityFromDTO(IngredienteDTO ingredienteDTO, @MappingTarget Ingrediente entity);
}

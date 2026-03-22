package com.diner.diner.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.diner.diner.controllers.dto.CategoriaDTO;
import com.diner.diner.entities.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "menu.id", source = "menuId")
    @Mapping(target = "productos", ignore = true)
    Categoria toEntity(CategoriaDTO categoriaDTO);

    @Mapping(target = "menuId", source = "menu.id")
    CategoriaDTO tDto(Categoria categoria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "menu.id", source = "menuId")
    @Mapping(target = "productos", ignore = true)
    void UpdateEntityToDTO(CategoriaDTO categoriaDTO, @MappingTarget Categoria entity);
}

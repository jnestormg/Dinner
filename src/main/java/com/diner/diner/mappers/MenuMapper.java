package com.diner.diner.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.diner.diner.controllers.dto.MenuDTO;
import com.diner.diner.entities.Menu;

@Mapper(componentModel = "spring", uses={RestauranteMapper.class})
public interface MenuMapper {

    @Mapping(target="id", ignore=true)
    @Mapping(target="fechaCreacion", ignore=true)
    @Mapping(target="fechaActualizacion", ignore=true)
    @Mapping(target="restaurante.id", source="restauranteId")
    Menu toEntity(MenuDTO menuDTO);

    @Mapping(target="restauranteId", source="restaurante.id")
    MenuDTO toDTO (Menu menu);

    @Mapping(target="id", ignore=true)
    @Mapping(target="fechaCreacion", ignore=true)
    @Mapping(target="fechaActualizacion", ignore=true)
    @Mapping(target="restaurante", ignore=true)
    void UpdateEntityToDTO(MenuDTO menudto, @MappingTarget Menu entity);


    
}

package com.diner.diner.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.diner.diner.controllers.dto.RestauranteDTO;
import com.diner.diner.entities.Restaurante;

@Mapper(
    componentModel = "spring",
    uses={
        MenuMapper.class,
        DireccionMapper.class
    }
)
public interface RestauranteMapper {

    // DTO → Entity
    @Mapping(target = "id", ignore = true) // Ignorar el campo id al mapear de DTO a Entity
   // @Mapping(target = "direccion", source = "direccion") // Mapear el campo direccion del DTO al Entity
    @Mapping(target="fechaCreacion", ignore = true) // Ignorar el campo fechaCreacion al mapear de DTO a Entity
    @Mapping(target="fechaActualizacion", ignore = true) // Ignorar el campo fechaActualizacion al mapear de DTO a Entity
    @Mapping(target="menus", ignore=true)
    Restaurante toEntity(RestauranteDTO dto);

    // Entity → DTO
    //@Mapping(target="direccion", source="direccion")
    RestauranteDTO toDTO(Restaurante entity);


    //metodo para actualizar un restaurante existente con los datos de un DTO
    @Mapping(target = "id", ignore = true) // Ignorar el campo id al mapear de DTO a Entity
    @Mapping(target="fechaCreacion", ignore = true) // Ignorar el campo fechaCreacion al mapear de DTO a Entity
    @Mapping(target="fechaActualizacion", ignore = true) // Ignorar el campo fechaActualizacion al mapear de DTO a Entity
    @Mapping(target="menus", ignore=true)
    void updateEntityFromDTO(RestauranteDTO dto, @MappingTarget Restaurante entity);
}

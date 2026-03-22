package com.diner.diner.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.diner.diner.controllers.dto.ProductoDTO;
import com.diner.diner.controllers.dto.SubProductoDTO;
import com.diner.diner.entities.Producto;
import com.diner.diner.entities.SubProducto;

@Mapper(componentModel = "spring")
public interface SubProductoMapper {

    @Mapping(target="id", ignore=true)
    @Mapping(target="fechaCreacion", ignore=true)
    @Mapping(target="fechaActualizacion", ignore=true)
    @Mapping(target="producto.id", source="productoId")
    SubProducto toEntity(SubProductoDTO subProductoDTO);

    @Mapping(target="productoId", source="producto.id")
    SubProductoDTO toDTO(SubProducto subProducto);

    @Mapping(target="id", ignore=true)
    @Mapping(target="fechaCreacion", ignore=true)
    @Mapping(target="fechaActualizacion", ignore=true)
    @Mapping(target="producto.id", source="productoId")
    void updateEntityToDTO(SubProductoDTO productoDTO, @MappingTarget SubProducto entity);
}

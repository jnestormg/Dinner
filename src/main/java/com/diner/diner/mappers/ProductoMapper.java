package com.diner.diner.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.diner.diner.controllers.dto.ProductoDTO;
import com.diner.diner.entities.Producto;

@Mapper(componentModel = "spring", uses={CategoriaMapper.class, SubProductoMapper.class})
public interface ProductoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "categoria.id", source = "categoriaId")
    @Mapping(target = "subProducto", source = "subproductos")
    @Mapping(target = "ingredientes", ignore=true)
    Producto toEntity(ProductoDTO productoDTO);

    @Mapping(target = "categoriaId", source = "categoria.id")
    @Mapping(target = "subproductos", source = "subProducto")
    ProductoDTO toDTO(Producto producto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "categoria.id", source = "categoriaId")
    @Mapping(target = "ingredientes", ignore=true)
    void updateEntityFromDTO(ProductoDTO productoDTO, @MappingTarget Producto entity);
    
}

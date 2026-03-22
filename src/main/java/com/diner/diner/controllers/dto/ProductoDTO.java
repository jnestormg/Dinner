package com.diner.diner.controllers.dto;

import com.diner.diner.entities.Productos;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record ProductoDTO(

    @JsonProperty("id")
    @Null(message="El id debe ir vacio")
    Long id,

    @JsonProperty(value="nombre", required=true)
    @NotNull(message="El nombre es requerido")
    @Size(min=2, max=100, message="El nombre debe tener entre 2 y 100 caracteres")
    String nombre,

    @JsonProperty("descripcion")
    String descripcion,

    @JsonProperty("precio")
    Float precio,

    @JsonProperty("estado")
    Boolean estado
) {

    public ProductoDTO(Productos producto){
        this(
            producto.getId(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getEstado()
        );
    }
    
}

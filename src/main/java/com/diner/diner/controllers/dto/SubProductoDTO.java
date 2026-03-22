package com.diner.diner.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record SubProductoDTO(
    
    @JsonProperty(value="id", required=false)
    @Null(message="El id debe ir vacio")
    Long id,

    @NotNull(message="El nombre no puede ir vacio")
    @Size(min=2, max=50, message="El nombre debe tener entre 2 y 50 caracteres")
    String nombre,

    String descripcion,

    @Positive(message="El precio debe ser un numero positivo")
    Float precio,

    @JsonProperty(value="producto_id", required=true)
    @NotNull(message="El producto_id no debe estar vacio")
    Long productoId
) {
    
}

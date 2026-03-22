package com.diner.diner.controllers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record ProductoDTO(
    @JsonProperty(value = "id", required = false)
    @Null(message = "El id debe ir vacio")
    Long id,

    @NotNull(message = "El nombre no debe ir vacio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    String nombre,

    String descripcion,

    @NotNull(message = "El precio no debe ir vacio")
    @DecimalMin(value = "0.01", message = "El precio debe ser un valor positivo")
    Float precio,

    Boolean estado,

    @JsonProperty(value = "categoria_id", required = true)
    @NotNull(message = "El categoria_id no debe ir vacio")
    Long categoriaId,

    List<SubProductoDTO> subproductos




) {
    
}

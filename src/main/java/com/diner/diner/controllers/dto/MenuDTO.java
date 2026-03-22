package com.diner.diner.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record MenuDTO(
    @JsonProperty(value="id", required = false)
    @Null(message="El id   debe ir vacio")
    Long id,

    @JsonProperty("nombre")
    @NotNull(message="El nombre no debe ir vacio")
    @Size(min=2, max=50, message="El nombre debe contener entre 2 y 50 caracteres")
    String nombre,

    @JsonProperty("descripcion")
    String descripcion,

    @JsonProperty("restaurante_id")
    @NotNull(message="El restaurante_id no puede ir vacio")
    Long restauranteId
) {
    
}

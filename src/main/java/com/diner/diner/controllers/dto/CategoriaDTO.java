package com.diner.diner.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(

    @JsonProperty(value = "id", required = false)
    @Null(message = "El id debe ir vacio")
    Long id,

    @NotNull(message = "El nombre   no    puede ir vacio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    String nombre,

    String descripcion,

    @NotNull(message = "El estado no debe ir vacio")
    Boolean estado,

    @JsonProperty("menu_id")
    @NotNull(message = "El menu_id no debe ir vacio")
    Long menuId
) {
    
}

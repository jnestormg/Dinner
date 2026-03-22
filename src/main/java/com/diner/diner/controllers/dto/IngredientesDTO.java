package com.diner.diner.controllers.dto;

import com.diner.diner.entities.Ingredientes;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record IngredientesDTO(
    @JsonProperty("id")
    @Null(message="El ID debe ir vacio")
    Long id,

    @JsonProperty(value="nombre", required=true)
    @NotNull(message="El nombre no debe ir vacio")
    @Size(min=2, max=50, message="El nombre debe tener entre 2 y 50 caracteres")
    String nombre,

    @JsonProperty("descripcion")
    String descripcion,

    @JsonProperty("estado")
    Boolean estado
) {

    public IngredientesDTO(Ingredientes ingrediente){
        this(
            ingrediente.getId(),
            ingrediente.getNombre(),
            ingrediente.getDescripcion(),
            ingrediente.getEstado()
        );
    }
    
}

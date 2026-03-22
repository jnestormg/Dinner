package com.diner.diner.controllers.dto;

import com.diner.diner.entities.Subproductos;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record SubproductoDTO(
    @JsonProperty("id")
    @Null(message="El id debe ir vacio")
    Long id,

    @JsonProperty(value="nombre", required=true)
    @NotNull(message="El nombre no debe ir vacio")
    @Size(min=2, max=100, message="El nombre debe tener entre 2 y 100 caracteres")
    String nombre,

    @JsonProperty("descripcion")
    String descripcion,

    @JsonProperty("precio")
    Float precio
) {

    public SubproductoDTO(Subproductos subproducto){
        this(
            subproducto.getId(),
            subproducto.getNombre(),
            subproducto.getDescripcion(),
            subproducto.getPrecio()
        );
    }
    
}

package com.diner.diner.controllers.dto;

import com.diner.diner.entities.Categorias;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(

    @JsonProperty("id")
    @Null(message="El id debe ir vacio")
    Long id,

    @JsonProperty(value="nombre", required=true)
    @NotNull(message="El nombre es requerido")
    @Size(min=3, max=100, message="El nombre debe tener entre 3 y 100 caracteres")
    String nombre,

    @JsonProperty("descripcion")
    String descripcion

) {

    public CategoriaDTO(Categorias categorias){
        this(categorias.getId(),
         categorias.getNombre(),
          categorias.getDescripcion()
        );
    }
    
}

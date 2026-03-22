package com.diner.diner.controllers.dto;

import com.diner.diner.entities.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record MenuDTO(
    @JsonProperty(value = "nombre", required = true)
    @NotNull(message = "El nombre del menú es obligatorio")
    String nombre,

    @JsonProperty("descripcion")
    String descripcion,

    @JsonProperty("id")
    @Null( message= "El id debe ir vacio")
    Long id

) {

    public MenuDTO(Menu menu) {
        this(
            menu.getNombre(),
            menu.getDescripcion(),
            menu.getId()
        );
    }   
    
}

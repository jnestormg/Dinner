package com.diner.diner.controllers.dto;

import java.util.List;

import com.diner.diner.entities.Restaurante;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RestauranteDTO(


    @Null(message = "El id del restaurante debe ser nulo al crear un nuevo restaurante")
    Long id,

    @JsonProperty(required = true, value = "nombre")
    @NotNull(message = "El nombre del restaurante es obligatorio")
    @Size(min = 1, max = 150, message = "El nombre del restaurante debe tener entre 1 y 150 caracteres")    
    String nombre,

    @JsonProperty("telefono")
    Long telefono,

    @JsonProperty("correo")
    @Email(message = "El correo debe ser una dirección de correo electrónico válida")
    String correo,

    @JsonProperty("direccion")
    @Valid
    DireccionDTO direccion,

    @JsonProperty("menus")
    @Valid
    List<MenuDTO> menus
) {
    public RestauranteDTO(Restaurante restaurante) {
        this(
            restaurante.getId(),
            restaurante.getNombre(),
            restaurante.getTelefono(),
            restaurante.getCorreo(),
            restaurante.getDireccion() != null ? new DireccionDTO(restaurante.getDireccion()) : null,
            restaurante.getMenus() != null ? 
                restaurante.getMenus().stream()
                    .map(menu -> new MenuDTO(menu))
                    .toList() : null
        );
    }
    
}

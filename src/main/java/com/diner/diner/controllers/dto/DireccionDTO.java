package com.diner.diner.controllers.dto;

import com.diner.diner.entities.Direccion;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Size;

public record DireccionDTO(
    @JsonProperty("calle")
    @Size(min = 1, max = 50, message = "La calle debe tener entre 1 y 50 caracteres")
    String calle,

    @JsonProperty("numero")
    String numero,

    @JsonProperty("colonia")
    @Size(min=1, max=50, message="La colonia debe tener entre 1 y 50 caracteres")
    String colonia,

    @JsonProperty("ciudad")
    @Size(min=1, max=50, message="La ciudad debe tener entre 1 y 50 caracteres")
    String ciudad,

    @JsonProperty("estado")
    @Size(min=1, max=40, message="El estado debe tener entre 1 y 40 caracteres")    
    String estado,

    @JsonProperty("codigo_postal")
    String codigoPostal
) {

    public DireccionDTO(Direccion direccion) {
        this(
            direccion.getCalle(),
            direccion.getNumero() != null ? direccion.getNumero().toString() : null,
            direccion.getColonia(),
            direccion.getCiudad(),
            direccion.getEstado(),
            direccion.getCodigoPostal() != null ? direccion.getCodigoPostal().toString() : null
        );
    }   
    
}

package com.diner.diner.entities;

import com.diner.diner.controllers.dto.DireccionDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {

    @Column(length=50)
    private String calle;

    @Column(length=14)
    private Integer numero;

    @Column(length=50)
    private String colonia;

    @Column(length=40)
    private String ciudad;
    
    @Column(length=40)
    private String estado;

    @Column(length=10)
    private Integer codigoPostal;

    public Direccion(DireccionDTO direccionDTO) {
        this.calle = direccionDTO.calle();
        this.numero = direccionDTO.numero() != null ? Integer.parseInt(direccionDTO.numero()) : null;
        this.colonia = direccionDTO.colonia();
        this.ciudad = direccionDTO.ciudad();
        this.estado = direccionDTO.estado();
        this.codigoPostal = direccionDTO.codigoPostal() != null ? Integer.parseInt(direccionDTO.codigoPostal()) : null;
    }
}

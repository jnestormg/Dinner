package com.diner.diner.service.Implements;

import java.util.Optional;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;

import com.diner.diner.controllers.dto.IngredienteDTO;

public interface IngredienteServiceImp {
    
    Page<IngredienteDTO> mostrarIngredientes(Pageable pageable);

    IngredienteDTO crearIngrediente(IngredienteDTO ingredienteDTO);

    void eliminarIngrediente(Long id);

    Optional<IngredienteDTO> obtenerIngredientePorId(Long id);

    IngredienteDTO actualizarIngrediente(Long id, IngredienteDTO ingredienteDTO);
}

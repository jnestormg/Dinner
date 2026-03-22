package com.diner.diner.service.Implements;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.diner.diner.controllers.dto.CategoriaDTO;

public interface CategoriaServiceImp {
    
    Page<CategoriaDTO> mostrarCategorias(Pageable page);

    CategoriaDTO crearCategoria(CategoriaDTO categoria);

    void eliminarCategoria(Long id);

    Optional<CategoriaDTO> buscarCategoriaPorId(Long id);

    CategoriaDTO actualizarCategoria(CategoriaDTO categoria, Long id);
}

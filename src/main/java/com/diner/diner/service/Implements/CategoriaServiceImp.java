package com.diner.diner.service.Implements;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.diner.diner.controllers.dto.CategoriaDTO;

public interface CategoriaServiceImp {
    
    Page<CategoriaDTO> mostrarCategorias(Pageable pageable);

    CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO);

    void eliminarCategoria(Long id);

    Optional<CategoriaDTO> buscarCategoriaPorId(Long id);

    CategoriaDTO actualizarCategoria(Long id, CategoriaDTO categoriaDTO);
}

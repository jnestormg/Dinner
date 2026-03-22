package com.diner.diner.service.Implements;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.diner.diner.controllers.dto.SubProductoDTO;

public interface SubProductoServiceImp {
    
    Page<SubProductoDTO> mostrarSubProductos(Pageable pageable);

    SubProductoDTO crearSubProducto(SubProductoDTO subProductoDTO);

    void eliminarSubProducto(Long id);

    SubProductoDTO actualizarSubProducto(SubProductoDTO subProductoDTO, Long id);

    Optional<SubProductoDTO> buscarSubProductoPorId(Long id);
}

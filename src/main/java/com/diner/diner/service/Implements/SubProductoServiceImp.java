package com.diner.diner.service.Implements;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.diner.diner.controllers.dto.SubproductoDTO;

public interface SubProductoServiceImp {
    
    SubproductoDTO crearSubProducto(SubproductoDTO subproducto);

    void eliminarSubProducto(Long id);

    Optional<SubproductoDTO> buscarSubProductoPorId(Long id);

    Page<SubproductoDTO> mostrarSubProductos(Pageable page);

    SubproductoDTO actualizarSubProductos(SubproductoDTO subproducto, Long id);
}

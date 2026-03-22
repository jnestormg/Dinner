package com.diner.diner.service.Implements;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.diner.diner.controllers.dto.ProductoDTO;

public interface ProductoServiceImp {
    
    ProductoDTO crearProducto(ProductoDTO productoDTO);

    void eliminarProducto(Long id);

    Page<ProductoDTO> mostrarProductos(Pageable page);

    Optional<ProductoDTO> buscarCategoriaPorId(Long id);

    ProductoDTO actualizarProducto(ProductoDTO productoDTO, Long id);
}

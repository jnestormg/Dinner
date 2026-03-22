package com.diner.diner.service.Implements;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.diner.diner.controllers.dto.ProductoDTO;

public interface ProductoServiceImp {
    
    Page<ProductoDTO> mostrarProductos(Pageable pageable);

    ProductoDTO crearProducto(ProductoDTO productoDTO);

    void eliminarProducto(Long id);

    Optional<ProductoDTO> buscarProductoPorID(Long id);

    ProductoDTO actualizarProducto(ProductoDTO productoDTO, Long id);

}

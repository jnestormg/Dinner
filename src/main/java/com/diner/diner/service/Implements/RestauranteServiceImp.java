package com.diner.diner.service.Implements;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.diner.diner.controllers.dto.RestauranteDTO;

public interface RestauranteServiceImp {
    
    Page<RestauranteDTO> obtenerRestaurantes(Pageable pageable);

    RestauranteDTO crearRestaurante(RestauranteDTO restauranteDTO);

    void eliminarRestauranteById(Long id);

    Optional<RestauranteDTO>  obtenerRestauranteById(Long id);

    RestauranteDTO actualizarRestaurante(Long id, RestauranteDTO restauranteDTO);
    
}

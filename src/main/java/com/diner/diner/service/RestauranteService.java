package com.diner.diner.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diner.diner.controllers.dto.RestauranteDTO;
import com.diner.diner.entities.Restaurante;
import com.diner.diner.repositories.RestauranteRepository;
import com.diner.diner.service.Implements.RestauranteServiceImp;

@Service
public class RestauranteService implements RestauranteServiceImp {

    private  RestauranteRepository repository;

    public RestauranteService(RestauranteRepository repository){
        this.repository = repository;
        }

    @Override
    public Page<RestauranteDTO> obtenerRestaurantes(Pageable pageable) {
        Page<RestauranteDTO> restaurantes = repository.findAll(pageable)
        .map(restaurante -> new RestauranteDTO(restaurante));
        return restaurantes;
    }


    @Override
    public RestauranteDTO crearRestaurante(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = repository.save(new Restaurante(restauranteDTO));
        return new RestauranteDTO(restaurante);
    }

    @Override
    public void eliminarRestauranteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RestauranteDTO actualizarRestaurante(Long id, RestauranteDTO restauranteDTO) {
    
        Restaurante restauranteExistente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurante no encontrado con id: " + id));

        restauranteExistente.setNombre(restauranteDTO.nombre());
        restauranteExistente.setTelefono(restauranteDTO.telefono());
        restauranteExistente.setCorreo(restauranteDTO.correo());
        // Aquí podrías actualizar también la dirección y los menús si es necesario

      

        Restaurante restauranteActualizado = repository.save(restauranteExistente);
        return new RestauranteDTO(restauranteActualizado);

    }

    @Override
    public Optional<RestauranteDTO> obtenerRestauranteById(Long id) {
        return repository.findById(id)
            .map(restaurante -> new RestauranteDTO(restaurante));
    }

}

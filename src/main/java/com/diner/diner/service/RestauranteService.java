package com.diner.diner.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.diner.diner.controllers.dto.RestauranteDTO;
import com.diner.diner.entities.Restaurante;
import com.diner.diner.mappers.RestauranteMapper;
import com.diner.diner.repositories.RestauranteRepository;
import com.diner.diner.service.Implements.RestauranteServiceImp;



@Service
public class RestauranteService implements RestauranteServiceImp {

    private  RestauranteRepository repository;
    private RestauranteMapper mapper;

    public RestauranteService(RestauranteRepository repository, RestauranteMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
        }

    @Override
   /*  @Transactional(readOnly = true)
    public Page<RestauranteDTO> obtenerRestaurantes(String search, Pageable pageable) {
        Page<Restaurante> restaurantes;

        if (StringUtils.hasText(search)){
            restaurantes = repository.buscarPorTodo(search, pageable);
        }
        else{
            restaurantes = repository.findAll(pageable);
        }

        return restaurantes.map(mapper::toDTO);
    }*/

        @Transactional(readOnly = true)
public Page<RestauranteDTO> obtenerRestaurantes(
    String search,
    String nombre,
    String correo,
    String telefono,
    Pageable pageable
) {

    Specification<Restaurante> spec = Specification.where(null);

    //  BÚSQUEDA GLOBAL
    if (StringUtils.hasText(search)) {
        spec = spec.and((root, query, cb) ->
            cb.or(
                cb.like(cb.lower(root.get("nombre")), "%" + search.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("correo")), "%" + search.toLowerCase() + "%"),
                cb.like(root.get("telefono").as(String.class), "%" + search + "%")
            )
        );
    }

    //  FILTROS POR COLUMNA
    if (StringUtils.hasText(nombre)) {
        spec = spec.and((root, query, cb) ->
            cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%")
        );
    }

    if (StringUtils.hasText(correo)) {
        spec = spec.and((root, query, cb) ->
            cb.like(cb.lower(root.get("correo")), "%" + correo.toLowerCase() + "%")
        );
    }

    if (StringUtils.hasText(telefono)) {
        spec = spec.and((root, query, cb) ->
            cb.like(root.get("telefono").as(String.class), "%" + telefono + "%")
        );
    }

    Page<Restaurante> restaurantes = repository.findAll(spec, pageable);

    return restaurantes.map(mapper::toDTO);
}


    @Override
    @Transactional
    public RestauranteDTO crearRestaurante(RestauranteDTO restauranteDTO) {
        //Restaurante restaurante = repository.save(new Restaurante(restauranteDTO));
       // return new RestauranteDTO(restaurante);
        Restaurante restauranteEntity = mapper.toEntity(restauranteDTO);
        Restaurante nuevoRestaurante = repository.save(restauranteEntity);
        return mapper.toDTO(nuevoRestaurante);
    }

    @Override
    @Transactional
    public void eliminarRestauranteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public RestauranteDTO actualizarRestaurante(Long id, RestauranteDTO restauranteDTO) {
    
        /*Restaurante restauranteExistente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurante no encontrado con id: " + id));

        restauranteExistente.setNombre(restauranteDTO.nombre());
        restauranteExistente.setTelefono(restauranteDTO.telefono());
        restauranteExistente.setCorreo(restauranteDTO.correo());
        // Aquí podrías actualizar también la dirección y los menús si es necesario

      

        Restaurante restauranteActualizado = repository.save(restauranteExistente);
        return mapper.toDTO(restauranteActualizado);*/

        Restaurante restauranteExistente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurante no encontrado con id: " + id));

        mapper.updateEntityFromDTO(restauranteDTO, restauranteExistente);
        Restaurante restauranteActualizado = repository.save(restauranteExistente);
        return mapper.toDTO(restauranteActualizado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RestauranteDTO> obtenerRestauranteById(Long id) {
        return repository.findById(id)
            .map(mapper::toDTO);
    }

}

package com.diner.diner.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diner.diner.controllers.dto.RestauranteDTO;
import com.diner.diner.service.RestauranteService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/restaurantes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    /*
     * Endpoint para obtener una lista paginada de restaurantes. Si no hay
     * restaurantes disponibles, retorna un mensaje indicando que no hay contenido.
     */
    @GetMapping
    public ResponseEntity<?> obtenerRestaurantes(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "sort", required = false) String sort,

            // filtros por columna
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "correo", required = false) String correo,
            @RequestParam(name = "telefono", required = false) String telefono) 
            {
        Sort sorting = Sort.unsorted();

        if (sort != null) {
            String[] parts = sort.split(",");
            sorting = Sort.by(Sort.Direction.fromString(parts[1]), parts[0]);
        } else {
            sorting = Sort.by("nombre").ascending(); // default
        }

        Pageable pageable = PageRequest.of(page, size, sorting);

        // Pageable pageable = PageRequest.of(page, size,
        // Sort.by("nombre").ascending()); // ordenar por nombre de forma ascendente
        Page<RestauranteDTO> restaurantes = restauranteService.obtenerRestaurantes(search, nombre, correo, telefono, pageable);
        if (restaurantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(Map.of("mensaje", "No hay restaurantes disponibles"));
        }
        return ResponseEntity.ok(restaurantes);
    }

    /*
     * Endpoint para crear un nuevo restaurante. Valida que el nombre, teléfono y
     * correo sean proporcionados y cumplan con las restricciones de longitud. Si la
     * creación es exitosa, retorna el restaurante creado con un status 201 Created.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Solo usuarios con rol ADMIN pueden acceder
    public ResponseEntity<?> crearRestaurante(
            @Valid @RequestBody RestauranteDTO restauranteDTO) {
        RestauranteDTO nuevoRestaurante = restauranteService.crearRestaurante(restauranteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRestaurante);
    }

    /*
     * Endpoint para eliminar un restaurante por su ID. Si el restaurante no existe,
     * retorna un mensaje de error con status 404 Not Found. Si la eliminación es
     * exitosa, retorna un mensaje confirmando la eliminación.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRestaurantePorId(

            @PathVariable Long id) {
        // buscar el restaurante por id, si exite eliminarlo, sino retornar un mensaje
        // de error
        Optional<RestauranteDTO> restaurante = restauranteService.obtenerRestauranteById(id);

        if (restaurante.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Restaurante no encontrado con id: " + id));
        }
        restauranteService.eliminarRestauranteById(id);
        return ResponseEntity.ok(Map.of("mensaje", "Restaurante eliminado exitosamente"));
    }

    /*
     * Endpoint para actualizar un restaurante por su ID. Valida que el restaurante
     * exista antes de intentar actualizarlo. Si el restaurante no existe, retorna
     * un mensaje de error con status 404 Not Found. Si la actualización es exitosa,
     * retorna el restaurante actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRestaurante(
            @Valid @PathVariable Long id,
            @RequestBody RestauranteDTO restauranteDTO) {

        Optional<RestauranteDTO> restauranteExistente = restauranteService.obtenerRestauranteById(id);
        if (restauranteExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Restaurante no encontrado con id: " + id));
        }
        RestauranteDTO restauranteActualizado = restauranteService.actualizarRestaurante(id, restauranteDTO);
        return ResponseEntity.ok(restauranteActualizado);
    }

}

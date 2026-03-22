package com.diner.diner.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diner.diner.controllers.dto.ProductoDTO;
import com.diner.diner.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<?> mostrarProductos(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue ="10") int size 
    ){
        Pageable pageable= PageRequest.of(page, size, 
            Sort.by("nombre").ascending());

        Page<ProductoDTO> productos = service.mostrarProductos(pageable);

        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductoDTO productoDTO){
        ProductoDTO producto= service.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Optional<ProductoDTO> productoExistente = service.buscarProductoPorID(id);

        if (productoExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error:", "No se encontro el producto con el id: "+id));
        }

        service.eliminarProducto(id);

        return ResponseEntity.ok(Map.of("mensaje:", "Eliminado exitosamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(
        @Valid
        @RequestBody ProductoDTO productoDTO,
        @PathVariable Long id
    ){
        Optional<ProductoDTO> productoExistente = service.buscarProductoPorID(id);

        if (productoExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error:", "No se encontró el producto con el id: "+id));
        }

        ProductoDTO producto = service.actualizarProducto(productoDTO, id);
        return ResponseEntity.ok(producto);

    }

        @GetMapping("/nombre/{id}")
        public ResponseEntity<?> obtenerNombreProducto(@PathVariable Long id) {
           Optional<ProductoDTO> productoExistente = service.buscarProductoPorID(id);
            if (productoExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "No se encontró el producto con el id: " + id));
            }
            String nombre = service.obtenerNombreProducto(id);
            return ResponseEntity.ok(Map.of("nombre", nombre));
        }

}

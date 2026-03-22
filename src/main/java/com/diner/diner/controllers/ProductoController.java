package com.diner.diner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diner.diner.controllers.dto.ProductoDTO;
import com.diner.diner.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<?> mostrarProductos(){

        Pageable page= PageRequest.of(0, 10, 
            Sort.by("nombre").ascending());

        Page<ProductoDTO> productos = service.mostrarProductos(page);

        return ResponseEntity.ok(productos);
    }
    
    @PostMapping
    public ResponseEntity<?> crearProductos(
        @RequestBody ProductoDTO productoDTO
    ){
        ProductoDTO producto= service.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(producto);
    }
}

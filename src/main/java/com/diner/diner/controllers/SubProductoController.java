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

import com.diner.diner.controllers.dto.SubProductoDTO;
import com.diner.diner.service.SubProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subproductos")
public class SubProductoController {
    
    @Autowired
    private SubProductoService service;

    @GetMapping
    public ResponseEntity<?> mostrarSubProductos(){
        Pageable pageable= PageRequest.of(0, 10, 
            Sort.by("nombre").ascending()
        );
        Page<SubProductoDTO> subProductos = service.mostrarSubProductos(pageable);

        return  ResponseEntity.ok(subProductos);
    }

    @PostMapping
    public ResponseEntity<?> crearSubProducto(
        @Valid
        @RequestBody SubProductoDTO subProductoDTO
    ){
        SubProductoDTO nuevoSubProducto= service.crearSubProducto(subProductoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSubProducto);
    }
}

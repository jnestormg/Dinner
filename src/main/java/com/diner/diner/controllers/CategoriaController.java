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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diner.diner.controllers.dto.CategoriaDTO;
import com.diner.diner.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;
    
    @GetMapping
    public ResponseEntity<?> mostrarCategorias(){
        Pageable categorias =PageRequest.of(0, 10, 
            Sort.by("nombre").ascending()
        );
        Page<CategoriaDTO> categoriaDTO = service.mostrarCategorias(categorias);

        return ResponseEntity.ok(categoriaDTO);

    }

    @PostMapping
    public ResponseEntity<?> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO){
        CategoriaDTO categoria= service.crearCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id){
        Optional<CategoriaDTO> categoriaExistente = service.buscarCategoriaPorId(id);

        if (categoriaExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error:", "No se encontro la categoria con el id: "+id));
        }

        service.eliminarCategoria(id);
        return ResponseEntity.ok(Map.of("mensaje:", "Se eliminó correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(
        @Valid
        @RequestBody CategoriaDTO categoriaDTO,
        @PathVariable Long id
    ){
        Optional<CategoriaDTO> categoriaExistente = service.buscarCategoriaPorId(id);

        if (categoriaExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error:", "Categoria con id"+id+" no encontrada"));
        }

        CategoriaDTO categoriaNueva = service.actualizarCategoria(id, categoriaDTO);
        return ResponseEntity.ok(categoriaNueva);
    }
}

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

import com.diner.diner.controllers.dto.MenuDTO;
import com.diner.diner.service.MenuService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService service;

    @GetMapping()
    public ResponseEntity<?> mostrarMenus(){

        Pageable page= PageRequest.of(0, 10, 
            Sort.by("nombre").ascending()
        );

        Page<MenuDTO> menus= service.mostrarMenus(page);

        return ResponseEntity.ok(menus);
    
    }

    @PostMapping
    public ResponseEntity<MenuDTO> crearMenu(@RequestBody MenuDTO menudto){
        MenuDTO menu= service.crearMenu(menudto);
        return ResponseEntity.status(HttpStatus.CREATED).body(menu);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMenu(@PathVariable Long id){
        Optional<MenuDTO> menuBuscado= service.buscarPorId(id);

        if (menuBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "No se encontro el menú con el id: "+id)
            );
        }
        service.eliminarMenu(id);
        return ResponseEntity.ok(Map.of("mensaje", "El menu fue eliminado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMenu(
        @RequestBody MenuDTO menuDTO, @PathVariable Long id
    ){

        Optional<MenuDTO> menuBuscado= service.buscarPorId(id);

        if (menuBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error:", "Menu con id: "+id+" no encontrado"));
        }

        MenuDTO menuActualizado= service.actualizarMenu(menuDTO, id);
        return ResponseEntity.ok(menuActualizado);
    }
    
}

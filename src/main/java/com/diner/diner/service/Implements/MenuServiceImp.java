package com.diner.diner.service.Implements;

import java.util.Optional;

import com.diner.diner.controllers.dto.MenuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuServiceImp {

    MenuDTO crearMenu(MenuDTO menu);

    void eliminarMenu(Long id);

    Optional<MenuDTO> buscarPorId(Long id);

    MenuDTO actualizarMenu(MenuDTO menu, Long id);

    Page<MenuDTO> mostrarMenus(Pageable page);
    
}

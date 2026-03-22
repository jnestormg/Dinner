package com.diner.diner.service.Implements;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.diner.diner.controllers.dto.MenuDTO;

public interface MenuServiceImp {
    

    MenuDTO createMenu(MenuDTO menuDTO);

    Optional<MenuDTO> buscarMenuPorId(Long id); 

    void eliminarMenu(Long id);

    MenuDTO actualizarMenu(Long id, MenuDTO menuDTO);

    Page<MenuDTO> obtenerMenus(Pageable pageable);


}

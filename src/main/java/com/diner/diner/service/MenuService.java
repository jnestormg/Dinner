package com.diner.diner.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diner.diner.controllers.dto.MenuDTO;
import com.diner.diner.entities.Menu;
import com.diner.diner.repositories.MenuRepository;
import com.diner.diner.service.Implements.MenuServiceImp;

@Service
public class MenuService implements MenuServiceImp {

    private MenuRepository repository;

    public MenuService(MenuRepository repo) {
        this.repository = repo;
    }

    @Override
    public MenuDTO crearMenu(MenuDTO menu) {
        Menu nuevo = repository.save(new Menu(menu));
        return new MenuDTO(nuevo);
    }

    @Override
    public void eliminarMenu(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<MenuDTO> buscarPorId(Long id) {
        return repository.findById(id).map(menu -> new MenuDTO(menu));
    }

    @Override
    public MenuDTO actualizarMenu(MenuDTO menu, Long id) {
     
        Menu menubuscado= repository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontro el menu con el id: "+id));

        menubuscado.setNombre(menu.nombre());
        menubuscado.setDescripcion(menu.descripcion());

        Menu newMenu = repository.save(menubuscado);

        return new MenuDTO(newMenu);


    }

    @Override
    public Page<MenuDTO> mostrarMenus(Pageable page) {
      Page<MenuDTO> menus= repository.findAll(page)
      .map(menu-> new MenuDTO(menu));

      return menus;
    }

}

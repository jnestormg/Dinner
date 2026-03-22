package com.diner.diner.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diner.diner.controllers.dto.MenuDTO;
import com.diner.diner.entities.Menu;
import com.diner.diner.mappers.MenuMapper;
import com.diner.diner.repositories.MenuReposiroty;
import com.diner.diner.service.Implements.MenuServiceImp;

@Service
public class MenuService implements MenuServiceImp {

    private final MenuReposiroty repository;

    private final MenuMapper mapper;

    public MenuService(
        MenuReposiroty repository,
        MenuMapper mapper
    ){
        this.repository= repository;
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public MenuDTO createMenu(MenuDTO menuDTO) {
        Menu menu = mapper.toEntity(menuDTO);
        Menu menuSave = repository.save(menu);
        return mapper.toDTO(menuSave);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MenuDTO> buscarMenuPorId(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    @Transactional
    public void eliminarMenu(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public MenuDTO actualizarMenu(Long id, MenuDTO menuDTO) {
        Menu menuExistente = repository.findById(id)
        .orElseThrow(()->new RuntimeException("null"));

        mapper.UpdateEntityToDTO(menuDTO, menuExistente);
    
        Menu menuActualizado = repository.save(menuExistente);

        return mapper.toDTO(menuActualizado);
    
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MenuDTO> obtenerMenus(Pageable pageable) {
           
        Page<MenuDTO> menus = repository.findAll(pageable)
        .map(mapper::toDTO);
        return menus;
    }
    
}

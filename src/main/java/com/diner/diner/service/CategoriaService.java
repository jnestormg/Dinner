package com.diner.diner.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diner.diner.controllers.dto.CategoriaDTO;
import com.diner.diner.entities.Categoria;
import com.diner.diner.mappers.CategoriaMapper;
import com.diner.diner.repositories.CategoriaRepository;
import com.diner.diner.service.Implements.CategoriaServiceImp;

@Service
public class CategoriaService implements CategoriaServiceImp {

    private final CategoriaRepository repository;

    private final CategoriaMapper mapper;

    public CategoriaService(
        CategoriaRepository repository,
        CategoriaMapper mapper
    ){
        this.repository= repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaDTO> mostrarCategorias(Pageable pageable) {
       return repository.findAll(pageable)
        .map(mapper::tDto);
    }

    @Override
    @Transactional
    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria= mapper.toEntity(categoriaDTO);
        Categoria nuevCategoria = repository.save(categoria);
        return mapper.tDto(nuevCategoria);
    }

    @Override
    @Transactional
    public void eliminarCategoria(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriaDTO> buscarCategoriaPorId(Long id) {
        return repository.findById(id)
        .map(mapper::tDto);
    }

    @Override
    @Transactional
    public CategoriaDTO actualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
  
        Categoria  categoriaExistente = repository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontro la categoria con el id: "+id));

        mapper.UpdateEntityToDTO(categoriaDTO, categoriaExistente);

        Categoria categoriaNueva = repository.save(categoriaExistente);

        return mapper.tDto(categoriaNueva);
    }
    
}

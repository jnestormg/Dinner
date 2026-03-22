package com.diner.diner.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diner.diner.controllers.dto.CategoriaDTO;
import com.diner.diner.entities.Categorias;
import com.diner.diner.repositories.CategoriaRepository;
import com.diner.diner.service.Implements.CategoriaServiceImp;

@Service
public class CategoriaService implements CategoriaServiceImp {

    private CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository){
        this.repository = repository;
    }

    @Override
    public Page<CategoriaDTO> mostrarCategorias(Pageable page) {
       Page<CategoriaDTO> categorias = repository.findAll(page)
       .map(categoria->new CategoriaDTO(categoria));
       return categorias;
    }

    @Override
    public CategoriaDTO crearCategoria(CategoriaDTO categoriadto) {
       Categorias categoria = repository.save(new Categorias(categoriadto));
       return new CategoriaDTO(categoria);
    }

    @Override
    public void eliminarCategoria(Long id) {
       repository.deleteById(id);
    }

    @Override
    public Optional<CategoriaDTO> buscarCategoriaPorId(Long id) {
        return repository.findById(id).map(categoria-> new CategoriaDTO(categoria));
    }

    @Override
    public CategoriaDTO actualizarCategoria(CategoriaDTO categoria, Long id) {
       Categorias categoriaBuscada = repository.findById(id)
       .orElseThrow(()->new RuntimeException("No se encontró la categoria con el id : "+id));

       categoriaBuscada.setNombre(categoria.nombre());
       categoriaBuscada.setDescripcion(categoria.descripcion());

       Categorias nuevaCategoria = repository.save(categoriaBuscada);
       return new CategoriaDTO(nuevaCategoria);
    }
    
}

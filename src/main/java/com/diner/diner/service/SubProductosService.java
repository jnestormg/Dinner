package com.diner.diner.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diner.diner.controllers.dto.SubproductoDTO;
import com.diner.diner.entities.Subproductos;
import com.diner.diner.repositories.SubproductoRepository;
import com.diner.diner.service.Implements.SubProductoServiceImp;

@Service
public class SubProductosService implements SubProductoServiceImp {

    private SubproductoRepository repository;

    public SubProductosService(SubproductoRepository repository){
        this.repository = repository;
    }

    @Override
    public SubproductoDTO crearSubProducto(SubproductoDTO subproductodto) {
        Subproductos subproductos= repository.save(new Subproductos(subproductodto));
        return new SubproductoDTO(subproductos);
    }

    @Override
    public void eliminarSubProducto(Long id) {
        repository.deleteById(id);
   }

    @Override
    public Optional<SubproductoDTO> buscarSubProductoPorId(Long id) {
        return repository.findById(id).map(subproducto->new SubproductoDTO(subproducto));
    }

    @Override
    public Page<SubproductoDTO> mostrarSubProductos(Pageable page) {
        Page<SubproductoDTO> subproductos = repository.findAll(page)
        .map(subproduct->new SubproductoDTO(subproduct));
        return subproductos;
    }

    @Override
    public SubproductoDTO actualizarSubProductos(SubproductoDTO subproductoDTO, Long id) {
        Subproductos subproductosBuscado= repository.findById(id)
        .orElseThrow(()-> new RuntimeException("El subproducto con el id: "+id+" no existe"));

        subproductosBuscado.setNombre(subproductoDTO.nombre());
        subproductosBuscado.setDescripcion(subproductoDTO.descripcion());
        subproductosBuscado.setPrecio(subproductoDTO.precio());

        Subproductos nuevoSubProducto = repository.save(subproductosBuscado);

        return new SubproductoDTO(nuevoSubProducto);

    }
    
}

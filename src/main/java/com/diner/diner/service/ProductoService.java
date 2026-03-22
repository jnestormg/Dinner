package com.diner.diner.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diner.diner.controllers.dto.ProductoDTO;
import com.diner.diner.entities.Producto;
import com.diner.diner.mappers.ProductoMapper;
import com.diner.diner.producers.ProductoProducer;
import com.diner.diner.repositories.ProductoRepository;
import com.diner.diner.service.Implements.ProductoServiceImp;

@Service
public class ProductoService implements ProductoServiceImp {

    private final ProductoRepository repository;

    private final ProductoMapper mapper;

    private final ProductoProducer producer;

    public ProductoService(
        ProductoRepository repository,
        ProductoMapper mapper,
        ProductoProducer producer
    ){
        this.mapper = mapper;
        this.repository = repository;
        this.producer = producer;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductoDTO> mostrarProductos(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    @Transactional
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
      Producto producto = mapper.toEntity(productoDTO);

      //producer.enviarProducto(productoDTO);
      Producto nuevoProducto = repository.save(producto);
      return mapper.toDTO(nuevoProducto);
    }

    @Override
    @Transactional
    public void eliminarProducto(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> buscarProductoPorID(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    @Transactional
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO, Long id) {
        Producto productoExistente = repository.findById(id)
        .orElseThrow(()->new RuntimeException("No se encontro el producto con el id: "+id));

        mapper.updateEntityFromDTO(productoDTO, productoExistente);

        Producto nuevoProducto = repository.save(productoExistente);

        return mapper.toDTO(nuevoProducto);
    }

    public String obtenerNombreProducto(Long id) {
        return repository.obtenerNombre(id);
    }



    
}

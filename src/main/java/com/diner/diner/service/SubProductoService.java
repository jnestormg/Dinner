package com.diner.diner.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diner.diner.controllers.dto.SubProductoDTO;
import com.diner.diner.entities.Producto;
import com.diner.diner.entities.SubProducto;
import com.diner.diner.mappers.SubProductoMapper;
import com.diner.diner.repositories.ProductoRepository;
import com.diner.diner.repositories.SubProductoRepository;
import com.diner.diner.service.Implements.SubProductoServiceImp;

@Service
public class SubProductoService implements SubProductoServiceImp {

    private final SubProductoRepository repository;

    private final ProductoRepository productoRepository;

    private final SubProductoMapper mapper;

    public SubProductoService(
        SubProductoRepository repository,
        SubProductoMapper mapper,
        ProductoRepository productoRepository
    ){
        this.mapper = mapper;
        this.repository = repository;
        this.productoRepository = productoRepository;
    }
    

    @Override
    @Transactional(readOnly=true)
    public Page<SubProductoDTO> mostrarSubProductos(Pageable pageable) {
        return repository.findAll(pageable)
        .map(mapper::toDTO);
    }

    @Override
    @Transactional
    public SubProductoDTO crearSubProducto(SubProductoDTO subProductoDTO) {

        Optional<Producto> producto = productoRepository.findById(subProductoDTO.productoId()); 

        SubProducto subProductodto = mapper.toEntity(subProductoDTO);

         if(producto.isPresent()){
            subProductodto.setProducto(producto.get());
        }else{
            throw new RuntimeException("Producto no encontrado");
        }
        
        SubProducto subProducto = repository.save(subProductodto);
        return mapper.toDTO(subProducto);
    }

    @Override
    @Transactional
    public void eliminarSubProducto(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public SubProductoDTO actualizarSubProducto(SubProductoDTO subProductoDTO, Long id) {
        SubProducto subProductoExistente = repository.findById(id).orElseThrow(()-> new RuntimeException("Sub producto no encontrado"));

        mapper.updateEntityToDTO(subProductoDTO, subProductoExistente);

        SubProducto nuevoSubProducto = repository.save(subProductoExistente);

        return mapper.toDTO(nuevoSubProducto);

    }

    @Override
    @Transactional(readOnly=true)
    public Optional<SubProductoDTO> buscarSubProductoPorId(Long id) {
        return  repository.findById(id).map(mapper::toDTO);
    }
    
}

package com.diner.diner.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diner.diner.controllers.dto.ProductoDTO;
import com.diner.diner.entities.Productos;
import com.diner.diner.repositories.ProductoRepository;
import com.diner.diner.service.Implements.ProductoServiceImp;

@Service
public class ProductoService implements ProductoServiceImp{

    private ProductoRepository repository;

    public ProductoService(ProductoRepository repository){
        this.repository= repository;
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
       Productos producto = repository.save(new Productos(productoDTO));
       return new ProductoDTO(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
       repository.deleteById(id);
    }

    @Override
    public Page<ProductoDTO> mostrarProductos(Pageable page) {
     
        Page<ProductoDTO> productos= repository.findAll(page)
        .map(producto-> new ProductoDTO(producto));

        return productos;

    }

    @Override
    public Optional<ProductoDTO> buscarCategoriaPorId(Long id) {
        return repository.findById(id).map(producto->new ProductoDTO(producto));
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO, Long id) {
        Productos productoBuscado= repository.findById(id)
        .orElseThrow(()-> new RuntimeException("No fue encontrado el producto con el id : "+id));

        productoBuscado.setNombre(productoDTO.nombre());
        productoBuscado.setDescripcion(productoDTO.descripcion());
        productoBuscado.setPrecio(productoDTO.precio());
        productoBuscado.setEstado(productoDTO.estado());

        Productos producto = repository.save(productoBuscado);

        return new ProductoDTO(producto);

   }
    

}

package com.diner.diner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diner.diner.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
 
    @Procedure(name = "obtener_producto")
    String obtenerNombre(@Param("id") Long id);

}

package com.diner.diner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diner.diner.entities.Productos;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Long> {
    
}

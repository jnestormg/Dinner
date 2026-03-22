package com.diner.diner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diner.diner.entities.SubProducto;

@Repository
public interface SubProductoRepository extends JpaRepository<SubProducto, Long> {
    
}

package com.diner.diner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diner.diner.entities.Categorias;

@Repository
public interface CategoriaRepository extends  JpaRepository<Categorias, Long>{
    
}

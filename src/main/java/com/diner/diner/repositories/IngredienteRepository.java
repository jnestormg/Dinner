package com.diner.diner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diner.diner.entities.Ingredientes;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingredientes, Long>{
    
}

package com.diner.diner.repositories;
import com.diner.diner.entities.Restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository  extends JpaRepository<Restaurante, Long> {
    
}

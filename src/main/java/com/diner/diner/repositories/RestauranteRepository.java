package com.diner.diner.repositories;
import com.diner.diner.entities.Restaurante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository  extends JpaRepository<Restaurante, Long> {
    
 @Query("SELECT r FROM Restaurante r WHERE " +
       "LOWER(r.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
       "LOWER(r.correo) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
       "CAST(r.telefono AS string) LIKE CONCAT('%', :search, '%')")
    Page<Restaurante> buscarPorTodo(String search, Pageable pageable);
}

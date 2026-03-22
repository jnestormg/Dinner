package com.diner.diner.repositories;
import com.diner.diner.entities.Subproductos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubproductoRepository  extends JpaRepository<Subproductos, Long> {
    
}

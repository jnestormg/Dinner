package com.diner.diner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diner.diner.entities.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    
}

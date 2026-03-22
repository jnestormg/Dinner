package com.diner.diner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diner.diner.entities.Menu;

@Repository
public interface MenuReposiroty extends JpaRepository<Menu, Long> {
    
}

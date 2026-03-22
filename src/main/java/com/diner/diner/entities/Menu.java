package com.diner.diner.entities;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diner.diner.controllers.dto.MenuDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menus")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @Column(
        name = "nombre",
        nullable = false,
        columnDefinition = "VARCHAR2(100) DEFAULT 'Menu sin nombre'"
    )
    private String nombre;
    
    @Column(
        name = "descripcion",
        columnDefinition = "CLOB"
    )
    private String descripcion;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable=false, updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name="restaurante_id")
    private Restaurante restaurante;

    @OneToMany(mappedBy="menu", cascade=CascadeType.ALL)
    private List<Categorias> categorias;

    public Menu (MenuDTO menuDTO) {
        this.nombre = menuDTO.nombre();
        this.descripcion = menuDTO.descripcion();
    }
}

package com.diner.diner.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diner.diner.controllers.dto.RestauranteDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="restaurantes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="nombre", length= 150, nullable=false, unique=true)
    private String nombre;

    @Column(name= "telefono", length=15, unique = true)
    private Long telefono;

    @Column(name ="correo", length= 100, unique = true)
    private String correo;

    @Embedded
    private Direccion direccion;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable=false, updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name="fecha_actualizacion", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy="restaurante", cascade= CascadeType.ALL)
    private List<Menu> menus;

    public Restaurante(RestauranteDTO restauranteDTO) {
        this.nombre = restauranteDTO.nombre();
        this.telefono = restauranteDTO.telefono();
        this.correo = restauranteDTO.correo();
        this.direccion = restauranteDTO.direccion() != null ? new Direccion(restauranteDTO.direccion()) : null;
    }

    
}

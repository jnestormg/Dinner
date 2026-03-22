package com.diner.diner.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ingredientes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingrediente {

    @Id
    @GeneratedValue(strategy=jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(length=50, nullable=false)
    private String nombre;

    @Column(columnDefinition="CLOB")
    private String descripcion;

    @Column(columnDefinition="NUMBER(1) DEFAULT 1")
    private Boolean estado;

    @CreationTimestamp
    @Column(nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP") 
    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name="producto_id", nullable=false)
    private Producto producto;

}

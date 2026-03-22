package com.diner.diner.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diner.diner.controllers.dto.ProductoDTO;

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
@Table(name="productos")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Productos {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="productos", cascade=CascadeType.ALL)
    private List<Subproductos> subproductos;

    @OneToMany(mappedBy="productos", cascade=CascadeType.ALL)
    private List<Ingredientes> ingredientes;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categorias categoria;

    @Column(nullable=false, length=100)
    private String nombre;

    @Column(columnDefinition="CLOB")
    private String descripcion;

    @Column(columnDefinition="NUMBER(10,2) DEFAULT 0.0")
    private Float precio;

    @Column(columnDefinition="NUMBER(1) DEFAULT 1")
    private Boolean estado;    

    @CreationTimestamp
    @Column(nullable=false, updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion;

    public Productos(ProductoDTO productoDTO){
        this.nombre = productoDTO.nombre();
        this.descripcion = productoDTO.descripcion();
        this.precio =  productoDTO.precio();
        this.estado = productoDTO.estado();
    }
}

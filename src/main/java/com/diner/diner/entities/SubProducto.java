package com.diner.diner.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="subproductos")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubProducto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length=50, nullable=false)
    private String nombre;

    @Column(columnDefinition="CLOB")
    private String descripcion;

    @Column(columnDefinition="NUMBER(10,2) DEFAULT 0.0")
    private Float precio;

    @CreationTimestamp
    @Column(nullable=false, updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion;
    
    @ManyToOne
    @JoinColumn(name="producto_id", nullable=false)
    @JsonIdentityReference(alwaysAsId=true)
    private Producto producto;

    @PrePersist
    @PreUpdate //Si precio == null, se copia el precio del producto. Cuando se actualiza el subproducto
    public void setPrecioDefault(){
        if(precio == null && producto != null){
            precio = producto.getPrecio();
        }
        else if(precio == null){
            precio = 0.0f;
        }
    }
    
}

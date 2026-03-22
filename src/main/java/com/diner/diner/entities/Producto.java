package com.diner.diner.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PrePersist;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
    generator=ObjectIdGenerators.PropertyGenerator.class,
    property="id"
)
@NamedStoredProcedureQuery(
    name = "obtener_producto",
    procedureName = "obtener_producto",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "nombre", type = String.class)
    }
)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(columnDefinition = "CLOB")
    private String descripcion;

    @Column(nullable = false, columnDefinition = "NUMBER(10,2) DEFAULT 0.0")
    private Float precio;

    @Column(columnDefinition = "NUMBER(1) DEFAULT 1")
    private Boolean estado;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy="producto", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<SubProducto> subProducto;

    @OneToMany(mappedBy="producto", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Ingrediente> ingredientes;
    

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            estado = true;
        }
    }
    
}

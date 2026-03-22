package com.diner.diner.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diner.diner.controllers.dto.CategoriaDTO;

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
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name="categorias")
public class Categorias {
    

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="menu_id")
    private Menu menu;

    @OneToMany(mappedBy="categoria", cascade=CascadeType.ALL)
    private List<Productos> productos;

    @Column(name="nombre", nullable=false, length=100)
    private String nombre;

    @Column( name = "descripcion",
        columnDefinition = "CLOB"
    )
    private String descripcion;


    @CreationTimestamp
    @Column(nullable=false, updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion;

    public Categorias(CategoriaDTO categoriaDTO){
        this.nombre = categoriaDTO.nombre();
        this.descripcion = categoriaDTO.descripcion();
    }
}

package com.uade.tpo.backendGrupo6MieMa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Categoria {

    public Categoria() {}

    public Categoria(String description) {
        this.categoria_description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoria_idCategoria;

    @Column(nullable = false)
    private String categoria_description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categoria")
    @JsonManagedReference
    private List<Producto> productos;



}

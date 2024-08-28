package com.uade.tpo.backendGrupo6MieMa.entity;

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

    @Column
    private String categoria_description;

//    @OneToOne(mappedBy = "categoria")
//    private Producto product;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

}

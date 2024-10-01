package com.uade.tpo.backendGrupo6MieMa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marca_idMarca;

    @Column(nullable = false)
    private String marca_nombre;

    @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Producto> productos;


    public Marca() {}

    public Marca(String marca_nombre) {
        this.marca_nombre = marca_nombre;
    }
}

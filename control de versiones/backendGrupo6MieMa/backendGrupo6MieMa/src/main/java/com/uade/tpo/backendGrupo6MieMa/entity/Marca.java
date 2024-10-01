package com.uade.tpo.backendGrupo6MieMa.entity;


import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Data
@Entity
public class Marca {

    //Constructor de Marca
    public Marca() {}

    public Marca(String marca_nombre) {
        this.marca_nombre = marca_nombre;
    }


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long marca_idMarca;

    @Column
    private String marca_nombre;

    @OneToMany(mappedBy = "marca")
    private List<Producto> productos;
}

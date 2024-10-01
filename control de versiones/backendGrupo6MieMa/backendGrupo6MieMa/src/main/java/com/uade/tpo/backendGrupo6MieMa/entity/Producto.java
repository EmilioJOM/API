package com.uade.tpo.backendGrupo6MieMa.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producto_idProducto;

    @Column
    private String producto_nombre;

    @Column
    private String producto_descripcion;

    @Column
    private int producto_precio;

    @Column
    private int producto_stock;

    @Column
    private String producto_imagen_url;

    @ManyToOne
    @JoinColumn(name = "producto_idMarca", referencedColumnName = "marca_idMarca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "producto_idCategoria", referencedColumnName = "categoria_idCategoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    private List<Detalle> detalles;
}



package com.uade.tpo.backendGrupo6MieMa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(nullable = false)
    private float producto_descuento = 0.0f;

    @ManyToOne
    @JoinColumn(name = "producto_idMarca", referencedColumnName = "marca_idMarca")
    @JsonBackReference
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "producto_idCategoria", referencedColumnName = "categoria_idCategoria")
    @JsonBackReference
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    @JsonBackReference
    private List<Detalle> detalles;
}


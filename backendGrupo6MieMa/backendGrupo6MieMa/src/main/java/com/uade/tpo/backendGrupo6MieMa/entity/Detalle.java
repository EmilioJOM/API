package com.uade.tpo.backendGrupo6MieMa.entity;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalle_idDetalle;

    @Column
    private String detalle_estado;

    @ManyToOne
    @JoinColumn(name = "detalle_idOrden", referencedColumnName = "orden_idOrden")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "detalle_idProducto", referencedColumnName = "producto_idProducto")
    private Producto producto;
}


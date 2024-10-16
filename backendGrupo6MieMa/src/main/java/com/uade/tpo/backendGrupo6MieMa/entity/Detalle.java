package com.uade.tpo.backendGrupo6MieMa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column
    private int detalle_cantidad;

    @Column
    private double detalle_precio;

    @ManyToOne
    @JoinColumn(name = "detalle_idOrden", referencedColumnName = "orden_idOrden")
    @JsonManagedReference
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "detalle_idProducto", referencedColumnName = "producto_idProducto")
    @JsonManagedReference
    private Producto producto;
}


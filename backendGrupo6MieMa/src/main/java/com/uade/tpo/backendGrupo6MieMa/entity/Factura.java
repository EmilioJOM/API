package com.uade.tpo.backendGrupo6MieMa.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facturaId;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Relación con el usuario

    @OneToOne
    @JoinColumn(name = "orden_id")
    private Orden orden; // Relación con la orden

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")  // Añadimos la clave foránea en Detalle
    private List<Detalle> detalles;

    @Column(nullable = false)
    private LocalDate fechaEmision;

    @Column(nullable = false)
    private double total; // Total de la factura

}

package com.uade.tpo.backendGrupo6MieMa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orden_idOrden;

    @Column
    private LocalDate orden_fechaOrden;

    @Column
    private String orden_estado;

    @Column
    private double orden_subtotal;

    @ManyToOne
    @JoinColumn(name = "orden_idUsuario", referencedColumnName = "usuario_idUsuario")
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "orden")
    @JsonBackReference
    private List<Detalle> detalles;
}


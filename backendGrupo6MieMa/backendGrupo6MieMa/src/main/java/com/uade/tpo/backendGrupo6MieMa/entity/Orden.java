package com.uade.tpo.backendGrupo6MieMa.entity;

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

    @ManyToOne
    @JoinColumn(name = "orden_idUsuario", referencedColumnName = "usuario_idUsuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "orden")
    private List<Detalle> detalles;
}


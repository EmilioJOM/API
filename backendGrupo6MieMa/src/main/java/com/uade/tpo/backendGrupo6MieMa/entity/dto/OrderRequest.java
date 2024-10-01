package com.uade.tpo.backendGrupo6MieMa.entity.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderRequest {
    private LocalDate ordenFechaOrden;
    private String ordenEstado;
    private Long usuarioId;
}
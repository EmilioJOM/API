package com.uade.tpo.backendGrupo6MieMa.entity.dto;


import lombok.Data;

@Data

public class DetalleRequest {
    private int cantidad;
    private Long productoId;
    private String estado;
}
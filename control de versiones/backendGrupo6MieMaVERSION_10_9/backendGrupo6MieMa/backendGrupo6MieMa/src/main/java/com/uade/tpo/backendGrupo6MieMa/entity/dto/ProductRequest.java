package com.uade.tpo.backendGrupo6MieMa.entity.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String productoNombre;
    private String productoDescripcion;
    private int productoPrecio;
    private int productoStock;
    private String productoImagenUrl;
    private Long marcaId;
    private Long categoriaId;
    private Float productoDescuento; // Agregado el campo descuento
}

package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.entity.Producto;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.ProductRequest;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    Producto crearProducto(ProductRequest productRequest);
    List<Producto> obtenerTodosProductos();
    void deleteAllProductos();
    Optional<Producto> getProductoById(Long productoId);
    Producto actualizarStock(Long productoId, int nuevoStock);
    Producto aplicarDescuento(Long productoId, float descuento);
    Producto actualizarPrecio(Long id, int nuevoPrecio);
    void deleteProductoById(Long productoId);
}
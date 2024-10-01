package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.entity.Categoria;
import com.uade.tpo.backendGrupo6MieMa.entity.Marca;
import com.uade.tpo.backendGrupo6MieMa.entity.Producto;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.ProductRequest;
import com.uade.tpo.backendGrupo6MieMa.repository.CategoryRepository;
import com.uade.tpo.backendGrupo6MieMa.repository.MarcaRepository;
import com.uade.tpo.backendGrupo6MieMa.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Producto crearProducto(ProductRequest productRequest) {
        Producto producto = new Producto();
        producto.setProducto_nombre(productRequest.getProductoNombre());
        producto.setProducto_descripcion(productRequest.getProductoDescripcion());
        producto.setProducto_precio(productRequest.getProductoPrecio());
        producto.setProducto_stock(productRequest.getProductoStock());
        producto.setProducto_imagen_url(productRequest.getProductoImagenUrl());

        // Buscar la marca por ID
        Optional<Marca> marca = marcaRepository.findById(productRequest.getMarcaId());
        marca.ifPresent(producto::setMarca);

        // Buscar la categoría por ID
        Optional<Categoria> categoria = categoryRepository.findById(productRequest.getCategoriaId());
        categoria.ifPresent(producto::setCategoria);

        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public void deleteAllProductos() {
        productoRepository.deleteAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long productoId) {
        return productoRepository.findById(productoId);
    }

    public Producto actualizarStock(Long id, int cantidad) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setProducto_stock(producto.getProducto_stock() + cantidad);
        return productoRepository.save(producto);
    }

    public Producto aplicarDescuento(Long productoId, float descuento) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setProducto_descuento(descuento);

        // Recalcular el precio con descuento
        float precioConDescuento = producto.getProducto_precio() * (1 - descuento / 100);
        producto.setProducto_precio((int) precioConDescuento);

        return productoRepository.save(producto);
    }

    public Producto actualizarPrecio(Long id, int nuevoPrecio) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        // Actualizar el precio del producto
        producto.setProducto_precio(nuevoPrecio);

        return productoRepository.save(producto);
    }

}

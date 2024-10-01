package com.uade.tpo.backendGrupo6MieMa.controllers;

import com.uade.tpo.backendGrupo6MieMa.entity.Producto;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.ProductRequest;
import com.uade.tpo.backendGrupo6MieMa.service.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Producto")
public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoService;

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductRequest productRequest) {
        Producto nuevoProducto = productoService.crearProducto(productRequest);
        return ResponseEntity.ok(nuevoProducto);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosProductos() {
        List<Producto> productos = productoService.obtenerTodosProductos();
        return ResponseEntity.ok(productos);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarTodosProductos(){
        productoService.deleteAllProductos();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long productoId) {
        Optional<Producto> result = productoService.getProductoById(productoId);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Producto> actualizarStock(@PathVariable Long id, @RequestParam int stock) {
        try {
            Producto productoActualizado = productoService.actualizarStock(id, stock);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}/descuento")
    public ResponseEntity<Producto> aplicarDescuento(@PathVariable Long id, @RequestParam float descuento) {
        Producto producto = productoService.aplicarDescuento(id, descuento);
        return ResponseEntity.ok(producto);
    }

    // Endpoint para actualizar el precio de un producto
    @PutMapping("/{id}/precio")
    public ResponseEntity<Producto> actualizarPrecio(@PathVariable Long id, @RequestParam int nuevoPrecio) {
        Producto productoActualizado = productoService.actualizarPrecio(id, nuevoPrecio);
        return ResponseEntity.ok(productoActualizado);
    }
}

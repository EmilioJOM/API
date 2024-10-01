package com.uade.tpo.backendGrupo6MieMa.controllers;

import com.uade.tpo.backendGrupo6MieMa.entity.Detalle;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.DetalleRequest;
import com.uade.tpo.backendGrupo6MieMa.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/Detalle")
public class DetalleController {

    @Autowired
    private DetalleService detalleService;

    @PostMapping
    public ResponseEntity<Detalle> crearDetalle(@RequestBody DetalleRequest detalleRequest) {
        Detalle nuevoDetalle = detalleService.crearDetalle(
                detalleRequest.getCantidad(),
                detalleRequest.getProductoId(),
                detalleRequest.getEstado()
        );
        return ResponseEntity.created(URI.create("/detalles/" + nuevoDetalle.getDetalle_idDetalle())).build();
    }

    @GetMapping
    public ResponseEntity<Page<Detalle>> obtenerTodosDetalles(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(detalleService.getDetalles(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(detalleService.getDetalles(PageRequest.of(page, size)));
    }

    @GetMapping("/{detalleId}")
    public ResponseEntity<Detalle> getDetalleById(@PathVariable Long detalleId) {
        Optional<Detalle> result = detalleService.getDetalleById(detalleId);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
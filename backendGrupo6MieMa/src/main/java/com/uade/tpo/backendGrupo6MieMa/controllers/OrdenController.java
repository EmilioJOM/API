package com.uade.tpo.backendGrupo6MieMa.controllers;

import com.uade.tpo.backendGrupo6MieMa.entity.Orden;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.DetalleRequest;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.OrderRequest;
import com.uade.tpo.backendGrupo6MieMa.service.OrdenService;
import com.uade.tpo.backendGrupo6MieMa.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Orden")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private DetalleService detalleService;

    @PostMapping
    public ResponseEntity<Orden> crearOrden(@RequestBody OrderRequest ordenRequest) {
        Orden nuevaOrden = ordenService.crearOrden(ordenRequest);
        return ResponseEntity.created(URI.create("/ordenes/" + nuevaOrden.getOrden_idOrden())).build();
    }

    @GetMapping
    public ResponseEntity<Page<Orden>> obtenerTodasOrdenes(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(ordenService.getOrdenes(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(ordenService.getOrdenes(PageRequest.of(page, size)));
    }

    @GetMapping("/{ordenId}")
    public ResponseEntity<Orden> getOrdenById(@PathVariable Long ordenId) {
        Optional<Orden> result = ordenService.getOrdenById(ordenId);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/{ordenId}/confirmar")
    public ResponseEntity<Void> confirmarOrden(@PathVariable Long ordenId) {
        ordenService.confirmarOrden(ordenId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{ordenId}/agregarDetalle")
    public ResponseEntity<Void> agregarDetalle(@PathVariable Long ordenId, @RequestBody List<DetalleRequest> detalleRequests) {
        ordenService.agregarDetalle(ordenId, detalleRequests);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarTodasOrdenes() {
        ordenService.deleteAllOrdenes();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
package com.uade.tpo.backendGrupo6MieMa.controllers;

import org.springframework.web.bind.annotation.*;

import com.uade.tpo.backendGrupo6MieMa.entity.Marca;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.BrandRequest;
import com.uade.tpo.backendGrupo6MieMa.exceptions.BrandDuplicateException;
import com.uade.tpo.backendGrupo6MieMa.service.MarcaService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/Marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> getMarcas(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(marcaService.getMarcas(PageRequest.of(0, Integer.MAX_VALUE)).getContent());
        return ResponseEntity.ok(marcaService.getMarcas(PageRequest.of(page, size)).getContent());
    }

    @GetMapping("/{marcaId}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable Long marcaId) {
        Optional<Marca> result = marcaService.getMarcaById(marcaId);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Object> createMarca(@RequestBody BrandRequest brandRequest)
            throws BrandDuplicateException {
        Marca result = marcaService.createMarca(brandRequest.getMarcaNombre());
        return ResponseEntity.created(URI.create("/marcas/" + result.getMarca_idMarca())).build();
    }

    @DeleteMapping("/deleteAllDataAndResetId")
    public ResponseEntity<Void> deleteAllDataAndResetId() {
        marcaService.deleteAllDataAndResetId();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{marcaId}")
    public ResponseEntity<Void> deleteMarcaById(@PathVariable Long marcaId) {
        marcaService.deleteMarcaById(marcaId);
        return ResponseEntity.noContent().build();
    }

}

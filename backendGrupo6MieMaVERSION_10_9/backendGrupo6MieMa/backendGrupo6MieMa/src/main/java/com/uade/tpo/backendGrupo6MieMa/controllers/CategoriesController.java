package com.uade.tpo.backendGrupo6MieMa.controllers;

import org.springframework.web.bind.annotation.*;

import com.uade.tpo.backendGrupo6MieMa.entity.Categoria;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.CategoryRequest;
import com.uade.tpo.backendGrupo6MieMa.exceptions.CategoryDuplicateException;
import com.uade.tpo.backendGrupo6MieMa.service.CategoryService;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/Categoria")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Categoria>> getCategories(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(categoryService.getCategories(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(categoryService.getCategories(PageRequest.of(page, size)));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Categoria> getCategoryById(@PathVariable Long categoryId) {
        Optional<Categoria> result = categoryService.getCategoryById(categoryId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest)
            throws CategoryDuplicateException {
        Categoria result = categoryService.createCategory(categoryRequest.getDescription());
        return ResponseEntity.created(URI.create("/categories/" + result.getCategoria_idCategoria())).build();
    }

    @DeleteMapping("/deleteNullDescriptions")
    public ResponseEntity<Void> deleteCategoriesWithNullDescription() {
        categoryService.deleteCategoriesWithNullDescription();
        return ResponseEntity.noContent().build();
    }

}

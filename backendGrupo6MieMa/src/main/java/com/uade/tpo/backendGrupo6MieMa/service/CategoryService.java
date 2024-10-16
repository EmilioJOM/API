package com.uade.tpo.backendGrupo6MieMa.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.backendGrupo6MieMa.entity.Categoria;
import com.uade.tpo.backendGrupo6MieMa.exceptions.CategoryDuplicateException;

public interface CategoryService {
    public Page<Categoria> getCategories(PageRequest pageRequest);

    public Optional<Categoria> getCategoryById(Long categoryId);

    public Categoria createCategory(String description) throws CategoryDuplicateException;

    void deleteCategoriesWithNullDescription();

    void deleteCategoryById(Long categoryId);
}
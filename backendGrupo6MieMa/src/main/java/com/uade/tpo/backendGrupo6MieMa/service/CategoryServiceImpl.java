package com.uade.tpo.backendGrupo6MieMa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.backendGrupo6MieMa.entity.Categoria;
import com.uade.tpo.backendGrupo6MieMa.exceptions.CategoryDuplicateException;
import com.uade.tpo.backendGrupo6MieMa.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Categoria> getCategories(PageRequest pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<Categoria> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Categoria createCategory(String description) throws CategoryDuplicateException {
        List<Categoria> categories = categoryRepository.findByDescription(description);
        if (categories.isEmpty())
            return categoryRepository.save(new Categoria(description));
        throw new CategoryDuplicateException();
    }

    public void deleteCategoriesWithNullDescription() {
        categoryRepository.deleteCategoriesWithNullDescription();
    }

    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}

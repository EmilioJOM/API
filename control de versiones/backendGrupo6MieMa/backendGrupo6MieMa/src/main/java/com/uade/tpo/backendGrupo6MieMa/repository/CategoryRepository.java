package com.uade.tpo.backendGrupo6MieMa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.backendGrupo6MieMa.entity.Categoria;

@Repository
public interface CategoryRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "select c from Categoria c where c.categoria_description = ?1")
    List<Categoria> findByDescription(String description);
}

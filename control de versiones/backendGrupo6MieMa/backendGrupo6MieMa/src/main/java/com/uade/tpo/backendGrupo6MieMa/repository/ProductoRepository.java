package com.uade.tpo.backendGrupo6MieMa.repository;

import com.uade.tpo.backendGrupo6MieMa.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

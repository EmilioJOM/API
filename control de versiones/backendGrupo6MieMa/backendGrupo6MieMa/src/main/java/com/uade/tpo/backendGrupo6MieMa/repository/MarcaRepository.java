package com.uade.tpo.backendGrupo6MieMa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uade.tpo.backendGrupo6MieMa.entity.Marca;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByMarcaNombre(String marca_nombre);
}

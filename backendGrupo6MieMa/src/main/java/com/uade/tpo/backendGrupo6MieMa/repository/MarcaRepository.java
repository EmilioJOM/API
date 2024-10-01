package com.uade.tpo.backendGrupo6MieMa.repository;

import com.uade.tpo.backendGrupo6MieMa.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}


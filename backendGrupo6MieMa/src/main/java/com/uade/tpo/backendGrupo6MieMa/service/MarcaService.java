package com.uade.tpo.backendGrupo6MieMa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.backendGrupo6MieMa.entity.Marca;
import com.uade.tpo.backendGrupo6MieMa.exceptions.BrandDuplicateException;

public interface MarcaService {

    Page<Marca> getMarcas(PageRequest pageRequest);

    Optional<Marca> getMarcaById(Long marcaId);

    Marca createMarca(String nombre) throws BrandDuplicateException;

    void deleteAllDataAndResetId();
}
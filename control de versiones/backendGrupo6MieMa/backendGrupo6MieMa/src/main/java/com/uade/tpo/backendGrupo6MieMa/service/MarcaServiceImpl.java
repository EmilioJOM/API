package com.uade.tpo.backendGrupo6MieMa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.backendGrupo6MieMa.entity.Marca;
import com.uade.tpo.backendGrupo6MieMa.exceptions.BrandDuplicateException;
import com.uade.tpo.backendGrupo6MieMa.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public Page<Marca> getMarcas(PageRequest pageRequest) {
        return marcaRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Marca> getMarcaById(Long marcaId) {
        return marcaRepository.findById(marcaId);
    }

    @Override
    public Marca createMarca(String nombre) throws BrandDuplicateException {
        List<Marca> marcas = marcaRepository.findByMarcaNombre(nombre);
        if (marcas.isEmpty()) {
            return marcaRepository.save(new Marca(nombre));
        }
        throw new BrandDuplicateException();
    }
}

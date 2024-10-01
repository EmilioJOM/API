package com.uade.tpo.backendGrupo6MieMa.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.uade.tpo.backendGrupo6MieMa.entity.Marca;
import com.uade.tpo.backendGrupo6MieMa.exceptions.BrandDuplicateException;
import com.uade.tpo.backendGrupo6MieMa.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        Marca nuevaMarca = new Marca(nombre);
        return marcaRepository.save(nuevaMarca);
    }

    public void deleteAllDataAndResetId() {
        marcaRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE marca AUTO_INCREMENT = 1");
    }
}

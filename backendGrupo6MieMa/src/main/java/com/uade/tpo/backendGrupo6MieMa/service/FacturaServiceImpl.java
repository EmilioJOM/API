package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.entity.Factura;
import com.uade.tpo.backendGrupo6MieMa.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> getFacturasByUsuario(Long usuarioId) {
        return facturaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Optional<Factura> getFacturaById(Long facturaId) {
        return facturaRepository.findById(facturaId);
    }

    @Override
    public Factura createFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public void deleteFactura(Long facturaId) {
        facturaRepository.deleteById(facturaId);
    }

    @Override
    public void deleteFacturasSinDetalles() {
        facturaRepository.deleteFacturasSinDetalles();
    }
}

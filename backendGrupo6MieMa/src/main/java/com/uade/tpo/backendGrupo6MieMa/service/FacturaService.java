package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.entity.Factura;

import java.util.List;
import java.util.Optional;

public interface FacturaService {

    List<Factura> getFacturasByUsuario(Long usuarioId);

    Optional<Factura> getFacturaById(Long facturaId);

    Factura createFactura(Factura factura);

    void deleteFactura(Long facturaId);

    void deleteFacturasSinDetalles();
}

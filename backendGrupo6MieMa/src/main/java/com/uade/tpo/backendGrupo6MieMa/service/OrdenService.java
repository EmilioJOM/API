package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.entity.Orden;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.DetalleRequest;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface OrdenService {
    Orden crearOrden(OrderRequest orderRequest);
    List<Orden> obtenerTodasOrdenes();
    List<Orden> getOrdenes();
    Optional<Orden> getOrdenById(Long ordenId);
    void deleteAllOrdenes();
    void confirmarOrden(Long ordenId);
    void agregarDetalle(Long ordenId, List<DetalleRequest> detalleRequests);
    void deleteOrdenById(Long ordenId);
}
package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.entity.Detalle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DetalleService {
    Detalle crearDetalle(Detalle detalle);
    Page<Detalle> getDetalles(PageRequest pageRequest);
    Optional<Detalle> getDetalleById(Long detalleId);
    List<Detalle> obtenerTodosDetalles();
    Detalle crearDetalle(int cantidad, Long productoId, String estado);
    void actualizarEstadoDetalle(Long detalleId, String estado);
}
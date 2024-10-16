package com.uade.tpo.backendGrupo6MieMa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.backendGrupo6MieMa.entity.Factura;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    // Obtener todas las facturas de un usuario por su ID
    @Query("SELECT f FROM Factura f WHERE f.usuario.usuario_idUsuario = ?1")
    List<Factura> findByUsuarioId(Long usuarioId);


    // Obtener una factura específica por su número de orden
    @Query("SELECT f FROM Factura f WHERE f.orden.orden_idOrden = ?1")
    Factura findByOrdenId(Long ordenId);

    // Eliminar facturas que no tienen detalles
    @Modifying
    @Transactional
    @Query("DELETE FROM Factura f WHERE f.detalles IS EMPTY")
    void deleteFacturasSinDetalles();
}
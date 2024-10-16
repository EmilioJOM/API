package com.uade.tpo.backendGrupo6MieMa.repository;

import com.uade.tpo.backendGrupo6MieMa.entity.Detalle;
import com.uade.tpo.backendGrupo6MieMa.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {
    List<Detalle> findByOrden(Orden orden);
    @Query("SELECT d FROM Detalle d WHERE d.orden.orden_idOrden = :ordenId")
    List<Detalle> findByOrden_Orden_idOrden(@Param("ordenId") Long ordenId);

}
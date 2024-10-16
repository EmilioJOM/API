package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.entity.Detalle;
import com.uade.tpo.backendGrupo6MieMa.entity.Producto;
import com.uade.tpo.backendGrupo6MieMa.repository.DetalleRepository;
import com.uade.tpo.backendGrupo6MieMa.repository.ProductoRepository;
import com.uade.tpo.backendGrupo6MieMa.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleServiceImpl implements DetalleService {

    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public Detalle crearDetalle(Detalle detalle) {
        return detalleRepository.save(detalle);
    }

    @Override
    public Page<Detalle> getDetalles(PageRequest pageRequest) {
        return detalleRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Detalle> getDetalleById(Long detalleId) {
        return detalleRepository.findById(detalleId);
    }

    @Override
    public List<Detalle> obtenerTodosDetalles() {
        return detalleRepository.findAll();
    }

    @Override
    public Detalle crearDetalle(int cantidad, Long productoId, String estado) {
        Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new IllegalArgumentException("Producto not found"));
        Detalle nuevoDetalle = new Detalle();
        nuevoDetalle.setDetalle_cantidad(cantidad);
        nuevoDetalle.setDetalle_precio(producto.getProducto_precio() * cantidad);
        nuevoDetalle.setProducto(producto);
        nuevoDetalle.setDetalle_estado(estado);
        return detalleRepository.save(nuevoDetalle);
    }

    @Override
    public void actualizarEstadoDetalle(Long detalleId, String estado) {
        Detalle detalle = detalleRepository.findById(detalleId).orElseThrow(() -> new IllegalArgumentException("Detalle not found"));
        detalle.setDetalle_estado(estado);
        detalleRepository.save(detalle);
    }



}
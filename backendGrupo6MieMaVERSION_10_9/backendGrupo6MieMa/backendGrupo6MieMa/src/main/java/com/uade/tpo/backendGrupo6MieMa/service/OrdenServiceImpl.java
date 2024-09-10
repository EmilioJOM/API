package com.uade.tpo.backendGrupo6MieMa.service;

import com.uade.tpo.backendGrupo6MieMa.entity.Detalle;
import com.uade.tpo.backendGrupo6MieMa.entity.Orden;
import com.uade.tpo.backendGrupo6MieMa.entity.Producto;
import com.uade.tpo.backendGrupo6MieMa.entity.Usuario;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.DetalleRequest;
import com.uade.tpo.backendGrupo6MieMa.entity.dto.OrderRequest;
import com.uade.tpo.backendGrupo6MieMa.repository.DetalleRepository;
import com.uade.tpo.backendGrupo6MieMa.repository.OrdenRepository;
import com.uade.tpo.backendGrupo6MieMa.repository.ProductoRepository;
import com.uade.tpo.backendGrupo6MieMa.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenServiceImpl implements OrdenService {

    private static final Logger logger = LoggerFactory.getLogger(OrdenServiceImpl.class);

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Orden> obtenerTodasOrdenes() {
        return ordenRepository.findAll();
    }

    @Override
    public Page<Orden> getOrdenes(PageRequest pageRequest) {
        return ordenRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Orden> getOrdenById(Long ordenId) {
        return ordenRepository.findById(ordenId);
    }

    @Override
    public void deleteAllOrdenes() {
        ordenRepository.deleteAll();
    }

    @Override
    @Transactional
    public Orden crearOrden(OrderRequest orderRequest) {
        Usuario user = usuarioRepository.findById(orderRequest.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));

        Orden orden = new Orden();
        orden.setOrden_fechaOrden(orderRequest.getOrdenFechaOrden());
        orden.setOrden_estado(orderRequest.getOrdenEstado());
        orden.setUsuario(user);
        orden.setOrden_subtotal(0.0); // Inicializar subtotal en 0

        return ordenRepository.save(orden);
    }

    @Override
    @Transactional
    public void confirmarOrden(Long ordenId) {
        logger.info("Iniciando confirmación de la orden con ID: {}", ordenId);

        // Obtener la orden
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> {
                    logger.error("Orden no encontrada con ID: {}", ordenId);
                    return new IllegalArgumentException("Orden no encontrada con ID: " + ordenId);
                });

        // Obtener detalles de la orden
        List<Detalle> detalles = detalleRepository.findByOrden_Orden_idOrden(ordenId);
        if (detalles == null || detalles.isEmpty()) {
            logger.error("No hay detalles para confirmar en la orden con ID: {}", ordenId);
            throw new IllegalArgumentException("No hay detalles para confirmar en la orden con ID: " + ordenId);
        }

        logger.info("Orden encontrada - ID: {}, Fecha: {}, Estado: {}, Subtotal inicial: {}",
                orden.getOrden_idOrden(), orden.getOrden_fechaOrden(), orden.getOrden_estado(), orden.getOrden_subtotal());

        // Crear y procesar detalles
        double subtotal = 0.0;
        for (Detalle detalle: detalles) {
            Producto producto = detalle.getProducto();
            logger.info("Procesando detalle - Producto ID: {}, Cantidad: {}, Estado: {}, Precio: {}",
                    producto.getProducto_idProducto(), detalle.getDetalle_cantidad(), detalle.getDetalle_estado(), detalle.getDetalle_precio());

            // Verificar Stock
            if (producto.getProducto_stock() < detalle.getDetalle_cantidad()) {
                logger.error("Stock insuficiente para el producto con ID: {}", producto.getProducto_idProducto());
                throw new IllegalArgumentException("Stock insuficiente para el producto con ID: " + producto.getProducto_idProducto());
            }

            // Actualizar Stock
            producto.setProducto_stock(producto.getProducto_stock() - detalle.getDetalle_cantidad());
            productoRepository.save(producto);
            logger.info("Producto actualizado - ID: {}, Nuevo Stock: {}", producto.getProducto_idProducto(), producto.getProducto_stock());

            // Calcular Subtotal
            subtotal += detalle.getDetalle_cantidad() * detalle.getDetalle_precio();
        }

        // Actualizar Orden
        orden.setOrden_subtotal(subtotal);
        orden.setOrden_estado("CONFIRMED");
        ordenRepository.save(orden);
        logger.info("Orden confirmada - ID: {}, Nuevo Subtotal: {}, Estado actualizado: CONFIRMED", orden.getOrden_idOrden(), subtotal);
    }



//        List<Detalle> detalles = detalleRequests.stream().map(detalleRequest -> {
//            logger.info("Procesando DetalleRequest - Producto ID: {}, Cantidad: {}, Estado: {}",
//                    detalleRequest.getProductoId(), detalleRequest.getCantidad(), detalleRequest.getEstado());
//
//            Producto producto = productoRepository.findById(detalleRequest.getProductoId())
//                    .orElseThrow(() -> {
//                        logger.error("Producto no encontrado con ID: {}", detalleRequest.getProductoId());
//                        return new IllegalArgumentException("Producto no encontrado con ID: " + detalleRequest.getProductoId());
//                    });
//
//            logger.info("Producto encontrado - ID: {}, Nombre: {}, Precio: {}, Stock disponible: {}",
//                    producto.getProducto_idProducto(), producto.getProducto_nombre(), producto.getProducto_precio(), producto.getProducto_stock());
//
//            // Validar cantidad
//            if (detalleRequest.getCantidad() <= 0) {
//                logger.error("Cantidad inválida ({}) para el producto: {} (ID: {})", detalleRequest.getCantidad(), producto.getProducto_nombre(), producto.getProducto_idProducto());
//                throw new IllegalArgumentException("Cantidad inválida para el producto: " + producto.getProducto_nombre());
//            }
//
//            // Verificar y actualizar stock
//            int nuevoStock = producto.getProducto_stock() - detalleRequest.getCantidad();
//            if (nuevoStock < 0) {
//                logger.error("Stock insuficiente para el producto: {} (ID: {}), Stock actual: {}, Cantidad solicitada: {}",
//                        producto.getProducto_nombre(), producto.getProducto_idProducto(), producto.getProducto_stock(), detalleRequest.getCantidad());
//                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getProducto_nombre());
//            }
//            producto.setProducto_stock(nuevoStock);
//            productoRepository.save(producto);
//
//            logger.info("Producto actualizado - ID: {}, Nuevo Stock: {}", producto.getProducto_idProducto(), nuevoStock);
//
//            // Crear detalle
//            double detallePrecio = producto.getProducto_precio() * detalleRequest.getCantidad();
//            logger.info("Precio del detalle calculado: {}", detallePrecio);
//
//            Detalle detalle = new Detalle();
//            detalle.setProducto(producto);
//            detalle.setOrden(orden);
//            detalle.setDetalle_cantidad(detalleRequest.getCantidad());
//            detalle.setDetalle_precio(detallePrecio);
//            detalle.setDetalle_estado(detalleRequest.getEstado());
//
//            return detalle;
//        }).collect(Collectors.toList());
//
//        if (detalles.isEmpty()) {
//            logger.error("No se pudieron crear detalles para la orden con ID: {}", ordenId);
//            throw new IllegalArgumentException("No se pudieron crear detalles para la orden");
//        }
//
//        // Guardar detalles
//        detalleRepository.saveAll(detalles);
//        logger.info("Detalles guardados para la orden con ID: {}", ordenId);
//
//        // Calcular el subtotal de la orden
//        double subtotal = detalles.stream()
//                .mapToDouble(Detalle::getDetalle_precio)
//                .sum();
//        logger.info("Subtotal de la orden calculado: {}", subtotal);
//
//        // Actualizar la orden
//        orden.setOrden_subtotal(subtotal);
//        orden.setOrden_estado("CONFIRMED");
//        ordenRepository.save(orden);
//
//        logger.info("Orden confirmada - ID: {}, Nuevo Subtotal: {}, Estado actualizado: CONFIRMED", orden.getOrden_idOrden(), subtotal);
//    }




//    @Override
//    @Transactional
//    public void confirmarOrden(Long ordenId, List<DetalleRequest> detalleRequests) {
//        // Log inicial de confirmación de orden
//        logger.info("Iniciando confirmación de la orden ID: {}", ordenId);
//
//        Orden orden = ordenRepository.findById(ordenId)
//                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada con ID: " + ordenId));
//
//        // Log del estado inicial de la orden
//        logger.info("Orden encontrada - ID: {}, Fecha: {}, Estado: {}, Subtotal inicial: {}",
//                orden.getOrden_idOrden(), orden.getOrden_fechaOrden(), orden.getOrden_estado(), orden.getOrden_subtotal());
//
//        if (detalleRequests.isEmpty()) {
//            logger.error("Error: No hay detalles para confirmar en la orden ID: {}", ordenId);
//            throw new IllegalArgumentException("No hay detalles para confirmar en la orden");
//        }
//
//        List<Detalle> detalles = detalleRequests.stream().map(detalleRequest -> {
//            logger.info("Procesando DetalleRequest - Producto ID: {}, Cantidad: {}, Estado: {}",
//                    detalleRequest.getProductoId(), detalleRequest.getCantidad(), detalleRequest.getEstado());
//
//            Producto producto = productoRepository.findById(detalleRequest.getProductoId())
//                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + detalleRequest.getProductoId()));
//
//            logger.info("Producto encontrado - ID: {}, Nombre: {}, Precio: {}, Stock disponible: {}",
//                    producto.getProducto_idProducto(), producto.getProducto_nombre(), producto.getProducto_precio(), producto.getProducto_stock());
//
//            // Validar cantidad
//            if (detalleRequest.getCantidad() <= 0) {
//                logger.error("Error: Cantidad inválida ({}) para el producto: {} (ID: {})", detalleRequest.getCantidad(), producto.getProducto_nombre(), producto.getProducto_idProducto());
//                throw new IllegalArgumentException("Cantidad inválida para el producto: " + producto.getProducto_nombre());
//            }
//
//            // Actualizar el stock del producto
//            int nuevoStock = producto.getProducto_stock() - detalleRequest.getCantidad();
//            if (nuevoStock < 0) {
//                logger.error("Error: Stock insuficiente para el producto: {} (ID: {}), Stock actual: {}, Cantidad solicitada: {}",
//                        producto.getProducto_nombre(), producto.getProducto_idProducto(), producto.getProducto_stock(), detalleRequest.getCantidad());
//                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getProducto_nombre());
//            }
//            producto.setProducto_stock(nuevoStock);
//            productoRepository.save(producto);
//
//            logger.info("Producto actualizado - ID: {}, Nuevo Stock: {}", producto.getProducto_idProducto(), nuevoStock);
//
//            // Calcular el precio del detalle
//            double detallePrecio = producto.getProducto_precio() * detalleRequest.getCantidad();
//            logger.info("Precio del detalle calculado: {}", detallePrecio);
//
//            Detalle detalle = new Detalle();
//            detalle.setProducto(producto);
//            detalle.setOrden(orden);
//            detalle.setDetalle_cantidad(detalleRequest.getCantidad());
//            detalle.setDetalle_precio(detallePrecio);
//            detalle.setDetalle_estado(detalleRequest.getEstado());
//
//            return detalle;
//        }).collect(Collectors.toList());
//
//        if (detalles.isEmpty()) {
//            logger.error("Error: No se pudieron crear detalles para la orden ID: {}", ordenId);
//            throw new IllegalArgumentException("No se pudieron crear detalles para la orden");
//        }
//
//        // Guardar los detalles
//        detalleRepository.saveAll(detalles);
//        logger.info("Detalles guardados para la orden ID: {}", ordenId);
//
//        // Calcular el subtotal de la orden
//        double subtotal = detalles.stream()
//                .mapToDouble(Detalle::getDetalle_precio)
//                .sum();
//        logger.info("Subtotal de la orden calculado: {}", subtotal);
//
//        // Actualizar la orden
//        orden.setOrden_subtotal(subtotal);
//        orden.setOrden_estado("CONFIRMED");
//        ordenRepository.save(orden);
//
//        logger.info("Orden confirmada - ID: {}, Nuevo Subtotal: {}, Estado actualizado: CONFIRMED", orden.getOrden_idOrden(), subtotal);
//    }


    @Override
    @Transactional
    public void agregarDetalle(Long ordenId, List<DetalleRequest> detalleRequests) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new IllegalArgumentException("Orden not found"));

        List<Detalle> detalles = detalleRequests.stream().map(detalleRequest -> {
            Producto producto = productoRepository.findById(detalleRequest.getProductoId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto not found"));

            Detalle detalle = new Detalle();
            detalle.setProducto(producto);
            detalle.setOrden(orden);
            detalle.setDetalle_cantidad(detalleRequest.getCantidad());
            detalle.setDetalle_precio(producto.getProducto_precio() * detalleRequest.getCantidad());
            detalle.setDetalle_estado(detalleRequest.getEstado());

            return detalle;
        }).collect(Collectors.toList());

        detalleRepository.saveAll(detalles);
    }
}
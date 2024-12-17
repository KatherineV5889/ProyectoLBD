package com.proyectobd.service;

import com.proyectobd.domain.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void agregarProducto(Producto producto) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("gestionar_productos.agregar_producto");
        query.registerStoredProcedureParameter("p_id_categoria", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_precio_producto", Double.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_stock_producto", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.IN);

        query.setParameter("p_id_categoria", producto.getIdCategoria());
        query.setParameter("p_nombre", producto.getNombre());
        query.setParameter("p_precio_producto", producto.getPrecioProducto());
        query.setParameter("p_stock_producto", producto.getStockProducto());
        query.setParameter("p_descripcion", producto.getDescripcion());
        query.setParameter("p_id_proveedor", producto.getIdProveedor());

        query.execute();
    }

    @Transactional
    public void modificarProducto(Producto producto) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("gestionar_productos.modificar_producto");
        query.registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_categoria", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_precio_producto", Double.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_stock_producto", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.IN);

        query.setParameter("p_id_producto", producto.getIdProducto());
        query.setParameter("p_id_categoria", producto.getIdCategoria());
        query.setParameter("p_nombre", producto.getNombre());
        query.setParameter("p_precio_producto", producto.getPrecioProducto());
        query.setParameter("p_stock_producto", producto.getStockProducto());
        query.setParameter("p_descripcion", producto.getDescripcion());
        query.setParameter("p_id_proveedor", producto.getIdProveedor());

        query.execute();
    }

    @Transactional
    public void eliminarProducto(Long idProducto) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("gestionar_productos.eliminar_producto");
        query.registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN);
        query.setParameter("p_id_producto", idProducto);
        query.execute();
    }

    @Transactional(readOnly = true)
    public List<Producto> listarProductos() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("gestionar_productos.listar_productos");
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Object[]> resultados = query.getResultList();
        List<Producto> productos = new ArrayList<>();

        for (Object[] fila : resultados) {
            Producto p = new Producto();
            p.setIdProducto(((Number) fila[0]).longValue());
            p.setIdCategoria(((Number) fila[1]).longValue());
            p.setNombre((String) fila[2]);
            p.setPrecioProducto(((Number) fila[3]).doubleValue());
            p.setStockProducto(((Number) fila[4]).intValue());
            p.setDescripcion((String) fila[5]);
            p.setIdProveedor(((Number) fila[6]).longValue());
            productos.add(p);
        }

        return productos;
    }

    @Transactional(readOnly = true)
    public Producto buscarPorId(Long id) {
        return listarProductos().stream()
                .filter(p -> p.getIdProducto().equals(id))
                .findFirst()
                .orElse(null);
    }
}

package com.proyectobd.dao;

import com.proyectobd.domain.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoDao {

    @PersistenceContext
    private EntityManager em;

    
    public void agregarProducto(Producto producto) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("agregar_producto");
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_categoria", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_precio_producto", Double.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_stock_producto", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.IN);

        query.setParameter("p_nombre", producto.getNombre());
        query.setParameter("p_id_categoria", producto.getIdCategoria());
        query.setParameter("p_precio_producto", producto.getPrecioProducto());
        query.setParameter("p_stock_producto", producto.getStockProducto());
        query.setParameter("p_descripcion", producto.getDescripcion());
        query.setParameter("p_id_proveedor", producto.getIdProveedor());

        query.execute();
    }

    
    public List<Producto> listarProductos() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("listar_productos", Producto.class);
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return query.getResultList();
    }

   
    public void modificarProducto(Producto producto) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("modificar_producto");
        query.registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_categoria", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_precio_producto", Double.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_stock_producto", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.IN);

        query.setParameter("p_id_producto", producto.getIdProducto());
        query.setParameter("p_nombre", producto.getNombre());
        query.setParameter("p_id_categoria", producto.getIdCategoria());
        query.setParameter("p_precio_producto", producto.getPrecioProducto());
        query.setParameter("p_stock_producto", producto.getStockProducto());
        query.setParameter("p_descripcion", producto.getDescripcion());
        query.setParameter("p_id_proveedor", producto.getIdProveedor());

        query.execute();
    }

    
    public void eliminarProducto(Long idProducto) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("eliminar_producto");
        query.registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN);
        query.setParameter("p_id_producto", idProducto);
        query.execute();
    }
}

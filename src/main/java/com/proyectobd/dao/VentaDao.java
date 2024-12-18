package com.proyectobd.dao;

import com.proyectobd.domain.Venta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VentaDao {

    @PersistenceContext
    private EntityManager em;

    public void agregarVenta(Venta venta) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("agregar_venta");
        query.registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_empleado", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_tienda", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_fecha", java.sql.Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_cantidad", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_total", Double.class, ParameterMode.IN);

        query.setParameter("p_id_cliente", venta.getCliente().getIdCliente());
        query.setParameter("p_id_empleado", venta.getEmpleado().getIdEmpleados());
        query.setParameter("p_id_producto", venta.getProducto().getIdProducto());
        query.setParameter("p_id_tienda", venta.getTienda().getIdTienda());
        query.setParameter("p_fecha", venta.getFecha());
        query.setParameter("p_cantidad", venta.getCantidad());
        query.setParameter("p_total", venta.getTotal());

        query.execute();
    }

    public List<Venta> listarVentas() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("listar_ventas", Venta.class);
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return query.getResultList();
    }

    public void modificarVenta(Venta venta) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("modificar_venta");
        query.registerStoredProcedureParameter("p_id_ventas", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_cantidad", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_total", Double.class, ParameterMode.IN);

        query.setParameter("p_id_ventas", venta.getIdVentas());
        query.setParameter("p_cantidad", venta.getCantidad());
        query.setParameter("p_total", venta.getTotal());

        query.execute();
    }

    public void eliminarVenta(Long idVenta) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("eliminar_venta");
        query.registerStoredProcedureParameter("p_id_ventas", Long.class, ParameterMode.IN);
        query.setParameter("p_id_ventas", idVenta);
        query.execute();
    }
}
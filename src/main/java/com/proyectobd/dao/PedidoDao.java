package com.proyectobd.dao;

import com.proyectobd.domain.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.util.Date;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoDao {

    @PersistenceContext
    private EntityManager em;

    public void agregarPedido(Pedido pedido) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("agregar_pedido");
        query.registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_fecha_pedido", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_total_unidades", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_tienda", Long.class, ParameterMode.IN);

        query.setParameter("p_id_proveedor", pedido.getIdProveedor());
        query.setParameter("p_fecha_pedido", pedido.getFechaPedido());
        query.setParameter("p_total_unidades", pedido.getTotalUnidades());
        query.setParameter("p_id_producto", pedido.getIdProducto());
        query.setParameter("p_id_tienda", pedido.getIdTienda());

        query.execute();
    }

    public List<Pedido> listarPedidos() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("listar_pedidos", Pedido.class);
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return query.getResultList();
    }

    public void modificarPedido(Pedido pedido) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("modificar_pedido");
        query.registerStoredProcedureParameter("p_id_pedido", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_fecha_pedido", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_total_unidades", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_tienda", Long.class, ParameterMode.IN);

        query.setParameter("p_id_pedido", pedido.getIdPedido());
        query.setParameter("p_id_proveedor", pedido.getIdProveedor());
        query.setParameter("p_fecha_pedido", pedido.getFechaPedido());
        query.setParameter("p_total_unidades", pedido.getTotalUnidades());
        query.setParameter("p_id_producto", pedido.getIdProducto());
        query.setParameter("p_id_tienda", pedido.getIdTienda());

        query.execute();
    }

    public void eliminarPedido(Long idPedido) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("eliminar_pedido");
        query.registerStoredProcedureParameter("p_id_pedido", Long.class, ParameterMode.IN);
        query.setParameter("p_id_pedido", idPedido);
        query.execute();
    }
}

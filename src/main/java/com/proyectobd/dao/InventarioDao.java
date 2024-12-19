package com.proyectobd.dao;

import com.proyectobd.domain.Inventario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventarioDao {

    @PersistenceContext
    private EntityManager em;

    public void agregarInventario(Inventario inventario) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("agregar_inventario");
        query.registerStoredProcedureParameter("p_total_productos", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_tienda", Long.class, ParameterMode.IN);

        query.setParameter("p_total_productos", inventario.getTotalProductos());
        query.setParameter("p_id_tienda", inventario.getIdTienda());

        query.execute();
    }

    public List<Inventario> listarInventarios() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("listar_inventarios", Inventario.class);
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return query.getResultList();
    }

    public void modificarInventario(Inventario inventario) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("modificar_inventario");
        query.registerStoredProcedureParameter("p_id_inventario", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_total_productos", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_tienda", Long.class, ParameterMode.IN);

        query.setParameter("p_id_inventario", inventario.getIdInventario());
        query.setParameter("p_total_productos", inventario.getTotalProductos());
        query.setParameter("p_id_tienda", inventario.getIdTienda());

        query.execute();
    }

    public void eliminarInventario(Long idInventario) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("eliminar_inventario");
        query.registerStoredProcedureParameter("p_id_inventario", Long.class, ParameterMode.IN);
        query.setParameter("p_id_inventario", idInventario);
        query.execute();
    }
}

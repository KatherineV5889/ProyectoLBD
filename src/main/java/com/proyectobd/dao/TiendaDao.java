package com.proyectobd.dao;

import com.proyectobd.domain.Tienda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TiendaDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    public void agregarTienda(String nombre, String direccion, String ciudad) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("agregar_tienda");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.setParameter(1, nombre);
        query.setParameter(2, direccion);
        query.setParameter(3, ciudad);
        query.execute();
    }

    public List<Tienda> listarTiendas() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("listar_tiendas", Tienda.class);
        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);

        query.execute();
        return query.getResultList();
    }

    public void modificarTienda(Long idTienda, String nombre, String direccion, String ciudad) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("modificar_tienda");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.setParameter(1, idTienda);
        query.setParameter(2, nombre);
        query.setParameter(3, direccion);
        query.setParameter(4, ciudad);
        query.execute();
    }

    public void eliminarTienda(Long idTienda) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_tienda");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.setParameter(1, idTienda);
        query.execute();
    }    
}

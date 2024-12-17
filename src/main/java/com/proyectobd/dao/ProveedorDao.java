package com.proyectobd.dao;

import com.proyectobd.domain.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProveedorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void agregarProveedor(String nombre, String descripcion, String ciudad, String telefono) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("agregar_proveedor");
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.setParameter(1, nombre);
        query.setParameter(2, descripcion);
        query.setParameter(3, ciudad);
        query.setParameter(4, telefono);
        query.execute();
    }

    public List<Proveedor> listarProveedores() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("listar_proveedores", Proveedor.class);
        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return query.getResultList();
    }

    public void modificarProveedor(Long idProveedor, String nombre, String descripcion, String ciudad, String telefono) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("modificar_proveedor");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        query.setParameter(1, idProveedor);
        query.setParameter(2, nombre);
        query.setParameter(3, descripcion);
        query.setParameter(4, ciudad);
        query.setParameter(5, telefono);
        query.execute();
    }

    public void eliminarProveedor(Long idProveedor) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_proveedor");
        query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        query.setParameter(1, idProveedor);
        query.execute();
    }
}

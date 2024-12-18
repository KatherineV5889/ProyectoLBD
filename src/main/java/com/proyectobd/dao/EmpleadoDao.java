package com.proyectobd.dao;

import com.proyectobd.domain.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpleadoDao {

    @PersistenceContext
    private EntityManager em;

    public void agregarEmpleado(Empleado empleado) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("agregar_empleado");
        query.registerStoredProcedureParameter("p_id_tienda", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_puesto", String.class, ParameterMode.IN);

        query.setParameter("p_id_tienda", empleado.getTienda().getIdTienda());
        query.setParameter("p_nombre", empleado.getNombre());
        query.setParameter("p_apellido", empleado.getApellido());
        query.setParameter("p_puesto", empleado.getPuesto());

        query.execute();
    }

    public List<Empleado> listarEmpleados() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("listar_empleados", Empleado.class);
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return query.getResultList();
    }

    public void modificarEmpleado(Empleado empleado) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("modificar_empleado");
        query.registerStoredProcedureParameter("p_id_empleados", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_tienda", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_puesto", String.class, ParameterMode.IN);

        query.setParameter("p_id_empleados", empleado.getIdEmpleados());
        query.setParameter("p_id_tienda", empleado.getTienda().getIdTienda());
        query.setParameter("p_nombre", empleado.getNombre());
        query.setParameter("p_apellido", empleado.getApellido());
        query.setParameter("p_puesto", empleado.getPuesto());

        query.execute();
    }

    public void eliminarEmpleado(Long idEmpleados) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("eliminar_empleado");
        query.registerStoredProcedureParameter("p_id_empleados", Long.class, ParameterMode.IN);
        query.setParameter("p_id_empleados", idEmpleados);
        query.execute();
    }
}

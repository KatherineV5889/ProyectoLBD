package com.proyectobd.service;

import com.proyectobd.domain.Empleado;
import com.proyectobd.domain.Tienda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
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

    @Transactional
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

    @Transactional
    public void eliminarEmpleado(Long idEmpleados) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("eliminar_empleado");
        query.registerStoredProcedureParameter("p_id_empleados", Long.class, ParameterMode.IN);
        query.setParameter("p_id_empleados", idEmpleados);
        query.execute();
    }

    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("listar_empleados");
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Object[]> resultados = query.getResultList();
        List<Empleado> empleados = new ArrayList<>();

        for (Object[] fila : resultados) {
            Empleado e = new Empleado();
            e.setIdEmpleados(((Number) fila[0]).longValue());

            Tienda t = new Tienda();
            t.setIdTienda(((Number) fila[1]).longValue());
            e.setTienda(t);

            e.setNombre((String) fila[2]);
            e.setApellido((String) fila[3]);
            e.setPuesto((String) fila[4]);
            empleados.add(e);
        }

        return empleados;
    }

    @Transactional(readOnly = true)
    public Empleado buscarPorId(Long id) {
        
        return listarEmpleados().stream()
                .filter(e -> e.getIdEmpleados().equals(id))
                .findFirst()
                .orElse(null);
    }
}

package com.proyectobd.dao;

import com.proyectobd.domain.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteDao {

    @PersistenceContext
    private EntityManager em;

    public void agregarCliente(Cliente cliente) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("agregar_cliente");
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_telefono", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN);

        query.setParameter("p_nombre", cliente.getNombre());
        query.setParameter("p_apellido", cliente.getApellido());
        query.setParameter("p_email", cliente.getEmail());
        query.setParameter("p_telefono", cliente.getTelefono());
        query.setParameter("p_direccion", cliente.getDireccion());

        query.execute();
    }

    public List<Cliente> listarClientes() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("listar_clientes", Cliente.class);
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();
        return query.getResultList();
    }

    public void modificarCliente(Cliente cliente) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("modificar_cliente");
        query.registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_telefono", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN);

        query.setParameter("p_id_cliente", cliente.getIdCliente());
        query.setParameter("p_nombre", cliente.getNombre());
        query.setParameter("p_apellido", cliente.getApellido());
        query.setParameter("p_email", cliente.getEmail());
        query.setParameter("p_telefono", cliente.getTelefono());
        query.setParameter("p_direccion", cliente.getDireccion());

        query.execute();
    }

    public void eliminarCliente(Long idCliente) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("eliminar_cliente");
        query.registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN);
        query.setParameter("p_id_cliente", idCliente);
        query.execute();
    }
}
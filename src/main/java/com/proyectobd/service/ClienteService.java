package com.proyectobd.service;

import com.proyectobd.domain.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
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

    @Transactional
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

    @Transactional
    public void eliminarCliente(Long idCliente) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("eliminar_cliente");
        query.registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN);
        query.setParameter("p_id_cliente", idCliente);
        query.execute();
    }

    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        StoredProcedureQuery query = em.createStoredProcedureQuery("listar_clientes");
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Object[]> resultados = query.getResultList();
        List<Cliente> clientes = new ArrayList<>();

        for (Object[] fila : resultados) {
            Cliente c = new Cliente();
            c.setIdCliente(((Number) fila[0]).longValue());
            c.setNombre((String) fila[1]);
            c.setApellido((String) fila[2]);
            c.setEmail((String) fila[3]);
            c.setTelefono((String) fila[4]);
            c.setDireccion((String) fila[5]);
            clientes.add(c);
        }

        return clientes;
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return listarClientes().stream()
                .filter(c -> c.getIdCliente().equals(id))
                .findFirst()
                .orElse(null);
    }
}


package com.proyectobd.service;

import com.proyectobd.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.proyectobd.dao.ClienteDao;

@Service
public class ClienteService {

    @Autowired
    private ClienteDao clientesDao;

    // Método para obtener todos los clientes
    public List<Cliente> getAllClientes() {
        return clientesDao.findAll();
    }

    // Método para obtener un cliente por ID
    public Optional<Cliente> getClienteById(Long id) {
        return clientesDao.findById(id);
    }

    // Método para guardar un nuevo cliente o actualizar uno existente
    public Cliente saveCliente(Cliente cliente) {
        return clientesDao.save(cliente);
    }

    // Método para eliminar un cliente por ID
    public void deleteCliente(Long id) {
        clientesDao.deleteById(id);
    }
}


package com.proyectobd.service;

import com.proyectobd.dao.ClientesDao;
import com.proyectobd.domain.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClientesDao clientesDao;

    // Método para obtener todos los clientes
    public List<Clientes> getAllClientes() {
        return clientesDao.findAll();
    }

    // Método para obtener un cliente por ID
    public Optional<Clientes> getClienteById(Long id) {
        return clientesDao.findById(id);
    }

    // Método para guardar un nuevo cliente o actualizar uno existente
    public Clientes saveCliente(Clientes cliente) {
        return clientesDao.save(cliente);
    }

    // Método para eliminar un cliente por ID
    public void deleteCliente(Long id) {
        clientesDao.deleteById(id);
    }
}


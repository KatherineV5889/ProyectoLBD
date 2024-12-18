package com.proyectobd.service;

import com.proyectobd.dao.ClienteDao;
import com.proyectobd.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    public List<Cliente> listarClientes() {
        return clienteDao.listarClientes();
    }

    public void guardarCliente(Cliente cliente) {
        if (cliente.getIdCliente() == null) {
            clienteDao.agregarCliente(cliente);
        } else {
            clienteDao.modificarCliente(cliente);
        }
    }

    public void eliminarCliente(Long id) {
        clienteDao.eliminarCliente(id);
    }
}

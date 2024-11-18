package com.proyectobd.service;

import com.proyectobd.dao.PedidoDao;
import com.proyectobd.domain.Pedido;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoDao pedidoDao;

    public List<Pedido> findAll() {
        return pedidoDao.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoDao.findById(id).orElse(null);
    }

    public void save(Pedido pedido) {
        pedidoDao.save(pedido);
    }

    public void eliminarPedido(Long id) {
        pedidoDao.deleteById(id);
    }
}

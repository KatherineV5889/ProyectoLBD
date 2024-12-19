package com.proyectobd.service;

import com.proyectobd.dao.PedidoDao;
import com.proyectobd.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoDao pedidoDao;

    public List<Pedido> listarPedidos() {
        return pedidoDao.listarPedidos();
    }

    public void guardarPedido(Pedido pedido) {
        if (pedido.getIdPedido() == null) {
            pedidoDao.agregarPedido(pedido);
        } else {
            pedidoDao.modificarPedido(pedido);
        }
    }

    public void eliminarPedido(Long idPedido) {
        pedidoDao.eliminarPedido(idPedido);
    }
}

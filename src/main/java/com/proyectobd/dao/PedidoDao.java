package com.proyectobd.dao;

import com.proyectobd.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoDao extends JpaRepository<Pedido, Long>{
    
}

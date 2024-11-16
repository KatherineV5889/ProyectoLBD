package com.proyectobd.dao;

import com.proyectobd.domain.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventarioDao extends JpaRepository<Inventario, Long>{
    
}

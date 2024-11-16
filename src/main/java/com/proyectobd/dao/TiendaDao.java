package com.proyectobd.dao;

import com.proyectobd.domain.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TiendaDao extends JpaRepository<Tienda, Long>{
    
}

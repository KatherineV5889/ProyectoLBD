package com.proyectobd.dao;

import com.proyectobd.domain.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesDao extends JpaRepository<Clientes, Long> {
    
}
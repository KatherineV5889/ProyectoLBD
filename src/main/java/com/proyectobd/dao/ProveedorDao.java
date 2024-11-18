package com.proyectobd.dao;

import com.proyectobd.domain.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProveedorDao extends JpaRepository<Proveedor, Long>{
    
}

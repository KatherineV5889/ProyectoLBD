package com.proyectobd.dao;

import com.proyectobd.domain.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosDao extends JpaRepository<Empleados, Long> {
   
}

package com.proyectobd.dao;

import com.proyectobd.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoDao extends JpaRepository<Empleado, Long> {
   
}

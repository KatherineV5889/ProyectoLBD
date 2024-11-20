package com.proyectobd.dao;

import com.proyectobd.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDao extends JpaRepository<Venta, Long> {
}

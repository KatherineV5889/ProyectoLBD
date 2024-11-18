
package com.proyectobd.dao;

import com.proyectobd.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaDao extends JpaRepository<Venta, Long> {
    // Métodos personalizados si son necesarios
}
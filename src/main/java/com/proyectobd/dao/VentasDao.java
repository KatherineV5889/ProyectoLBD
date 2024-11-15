
package com.proyectobd.dao;

import com.proyectobd.domain.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasDao extends JpaRepository<Ventas, Long> {
    // Métodos personalizados si son necesarios
}
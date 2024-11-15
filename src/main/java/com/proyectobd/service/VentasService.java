
package com.proyectobd.service;

import com.proyectobd.dao.VentasDAO;
import com.proyectobd.domain.Ventas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VentasService {

    @Autowired
    private VentasDAO ventasDAO;

    public List<Ventas> listarVentas() {
        return ventasDAO.findAll();
    }

    public Optional<Ventas> obtenerVentaPorId(Long id) {
        return ventasDAO.findById(id);
    }

    public Ventas guardarVenta(Ventas ventas) {
        return ventasDAO.save(ventas);
    }

    public void eliminarVenta(Long id) {
        ventasDAO.deleteById(id);
    }
}

package com.proyectobd.service;


import com.proyectobd.domain.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.proyectobd.dao.VentaDao;

@Service
public class VentaService {

    @Autowired
    private VentaDao ventasDao;

    public List<Venta> listarVentas() {
        return ventasDao.findAll();
    }

    public Optional<Venta> obtenerVentasPorId(Long id) {
        return ventasDao.findById(id);
    }

    public Venta guardarVentas(Venta ventas) {
        return ventasDao.save(ventas);
    }

    public void eliminarVentas(Long id) {
        ventasDao.deleteById(id);
    }
}
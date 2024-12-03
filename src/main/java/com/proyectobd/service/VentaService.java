package com.proyectobd.service;

import com.proyectobd.dao.VentaDao;
import com.proyectobd.domain.Venta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    private VentaDao ventaDao;

    public List<Venta> findAll() {
        return ventaDao.findAll();
    }

    public Venta findById(Long id) {
        return ventaDao.findById(id).orElse(null);
    }

    public void save(Venta venta) {
        ventaDao.save(venta);
    }

    public void eliminarVenta(Long id) {
        ventaDao.deleteById(id);
    }
}

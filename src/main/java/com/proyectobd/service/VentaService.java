package com.proyectobd.service;

import com.proyectobd.dao.VentaDao;
import com.proyectobd.domain.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaDao ventaDao;

    public List<Venta> listarVentas() {
        return ventaDao.listarVentas();
    }

    public void guardarVenta(Venta venta) {
        if (venta.getIdVentas() == null) {
            ventaDao.agregarVenta(venta);
        } else {
            ventaDao.modificarVenta(venta);
        }
    }

    public void eliminarVenta(Long idVenta) {
        ventaDao.eliminarVenta(idVenta);
    }
}
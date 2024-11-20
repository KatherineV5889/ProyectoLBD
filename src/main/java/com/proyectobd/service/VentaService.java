package com.proyectobd.service;

import com.proyectobd.dao.VentaDao;
import com.proyectobd.domain.Venta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaDao ventaDao;

    public VentaService(VentaDao ventaDao) {
        this.ventaDao = ventaDao;
    }

    public List<Venta> listarTodas() {
        return ventaDao.findAll();
    }

    public Optional<Venta> buscarPorId(Long id) {
        return ventaDao.findById(id);
    }

    public Venta guardar(Venta venta) {
        return ventaDao.save(venta);
    }

    public void eliminar(Long id) {
        ventaDao.deleteById(id);
    }
}

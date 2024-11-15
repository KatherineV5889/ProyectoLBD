
package com.proyectobd.service;


import com.proyectobd.domain.Ventas;
import com.proyectobd.dao.VentasDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VentasService {

    @Autowired
    private VentasDao ventasDao;

    public List<Ventas> listarVentas() {
        return ventasDao.findAll();
    }

    public Optional<Ventas> obtenerVentasPorId(Long id) {
        return ventasDao.findById(id);
    }

    public Ventas guardarVentas(Ventas ventas) {
        return ventasDao.save(ventas);
    }

    public void eliminarVentas(Long id) {
        ventasDao.deleteById(id);
    }
}
package com.proyectobd.service;

import com.proyectobd.dao.InventarioDao;
import com.proyectobd.domain.Inventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioDao inventarioDao;

    public List<Inventario> listarInventarios() {
        return inventarioDao.listarInventarios();
    }

    public void guardarInventario(Inventario inventario) {
        if (inventario.getIdInventario() == null) {
            inventarioDao.agregarInventario(inventario);
        } else {
            inventarioDao.modificarInventario(inventario);
        }
    }

    public void eliminarInventario(Long idInventario) {
        inventarioDao.eliminarInventario(idInventario);
    }
}

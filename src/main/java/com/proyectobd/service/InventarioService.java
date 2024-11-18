package com.proyectobd.service;

import com.proyectobd.dao.InventarioDao;
import com.proyectobd.domain.Inventario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {
    
    @Autowired
    private InventarioDao inventarioDao;

    public List<Inventario> findAll() {
        return inventarioDao.findAll();
    }

    public Inventario findById(Long id) {
        return inventarioDao.findById(id).orElse(null);
    }

    public void save(Inventario inventario) {
        inventarioDao.save(inventario);
    }

    public void eliminarInventario(Long id) {
        inventarioDao.deleteById(id);
    }
}

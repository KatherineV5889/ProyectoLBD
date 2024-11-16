package com.proyectobd.service;

import com.proyectobd.dao.ProveedorDao;
import com.proyectobd.domain.Proveedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {
    
    @Autowired
    private ProveedorDao proveedorDao;

    public List<Proveedor> findAll() {
        return proveedorDao.findAll();
    }

    public Proveedor findById(Long id) {
        return proveedorDao.findById(id).orElse(null);
    }

    public void save(Proveedor proveedor) {
        proveedorDao.save(proveedor);
    }

    public void eliminarProveedor(Long id) {
        proveedorDao.deleteById(id);
    }
    
}

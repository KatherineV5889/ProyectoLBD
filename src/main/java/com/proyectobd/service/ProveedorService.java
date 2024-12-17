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
        return proveedorDao.listarProveedores();
    }

    public void save(Proveedor proveedor) {
        if (proveedor.getIdProveedor() == null) {
            proveedorDao.agregarProveedor(
                proveedor.getNombre(),
                proveedor.getDescripcion(),
                proveedor.getCiudad(),
                proveedor.getTelefono()
            );
        } else {
            proveedorDao.modificarProveedor(
                proveedor.getIdProveedor(),
                proveedor.getNombre(),
                proveedor.getDescripcion(),
                proveedor.getCiudad(),
                proveedor.getTelefono()
            );
        }
    }

    public Proveedor findById(Long id) {
        List<Proveedor> proveedores = findAll();
        return proveedores.stream()
                .filter(p -> p.getIdProveedor().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void eliminarProveedor(Long id) {
        proveedorDao.eliminarProveedor(id);
    }
}

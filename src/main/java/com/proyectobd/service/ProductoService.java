package com.proyectobd.service;

import com.proyectobd.dao.ProductoDao;
import com.proyectobd.domain.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoDao productoDao;

    public List<Producto> findAll() {
        return productoDao.findAll();
    }

    public Producto findById(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    public void save(Producto producto) {
        productoDao.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoDao.deleteById(id);
    }
}

package com.proyectobd.service;

import com.proyectobd.dao.ProductoDao;
import com.proyectobd.domain.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoDao productoDao;

    public List<Producto> listarProductos() {
        return productoDao.listarProductos();
    }

    public void guardarProducto(Producto producto) {
        if (producto.getIdProducto() == null) {
            productoDao.agregarProducto(producto);
        } else {
            productoDao.modificarProducto(producto);
        }
    }

    public void eliminarProducto(Long idProducto) {
        productoDao.eliminarProducto(idProducto);
    }
}


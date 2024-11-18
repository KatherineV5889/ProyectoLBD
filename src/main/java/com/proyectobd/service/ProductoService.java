package com.proyectobd.service;

import com.proyectobd.domain.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.proyectobd.dao.ProductoDao;

@Service
public class ProductoService {

    @Autowired
    private ProductoDao productosDao;

    // Método para obtener todos los productos
    public List<Producto> getAllProductos() {
        return productosDao.findAll();
    }

    // Método para obtener un producto por ID
    public Optional<Producto> getProductoById(Long id) {
        return productosDao.findById(id);
    }

    // Método para guardar un nuevo producto o actualizar uno existente
    public Producto saveProducto(Producto producto) {
        return productosDao.save(producto);
    }

    // Método para eliminar un producto por ID
    public void deleteProducto(Long id) {
        productosDao.deleteById(id);
    }
}


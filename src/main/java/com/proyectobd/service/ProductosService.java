package com.proyectobd.service;

import com.proyectobd.dao.ProductosDao;
import com.proyectobd.domain.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService {

    @Autowired
    private ProductosDao productosDao;

    // Método para obtener todos los productos
    public List<Productos> getAllProductos() {
        return productosDao.findAll();
    }

    // Método para obtener un producto por ID
    public Optional<Productos> getProductoById(Long id) {
        return productosDao.findById(id);
    }

    // Método para guardar un nuevo producto o actualizar uno existente
    public Productos saveProducto(Productos producto) {
        return productosDao.save(producto);
    }

    // Método para eliminar un producto por ID
    public void deleteProducto(Long id) {
        productosDao.deleteById(id);
    }
}


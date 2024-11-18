package com.proyectobd.service;

import com.proyectobd.dao.CategoriaDao;
import com.proyectobd.domain.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaDao categoriaDao;

    public List<Categoria> findAll() {
        return categoriaDao.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaDao.findById(id).orElse(null);
    }

    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    public void eliminarCategoria(Long id) {
        categoriaDao.deleteById(id);
    }
    
}

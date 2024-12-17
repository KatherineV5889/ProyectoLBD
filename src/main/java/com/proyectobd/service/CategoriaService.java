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
        return categoriaDao.listarCategorias();
    }

    public void save(Categoria categoria) {
        if (categoria.getIdCategoria() == null) {
            categoriaDao.agregarCategoria(categoria.getNombre());
        } else {
            categoriaDao.modificarCategoria(categoria.getIdCategoria(), categoria.getNombre());
        }
    }
   
    public Categoria findById(Long id) {
        List<Categoria> categorias = findAll();
        return categorias.stream()
                .filter(c -> c.getIdCategoria().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void eliminarCategoria(Long id) {
        categoriaDao.eliminarCategoria(id);
    } 
}

package com.proyectobd.service;

import com.proyectobd.dao.TiendaDao;
import com.proyectobd.domain.Tienda;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiendaService {
    
    @Autowired
    private TiendaDao tiendaDao;

    public List<Tienda> findAll() {
        return tiendaDao.findAll();
    }

    public Tienda findById(Long id) {
        return tiendaDao.findById(id).orElse(null);
    }

    public void save(Tienda tienda) {
        tiendaDao.save(tienda);
    }

    public void eliminarTienda(Long id) {
        tiendaDao.deleteById(id);
    }
    
}

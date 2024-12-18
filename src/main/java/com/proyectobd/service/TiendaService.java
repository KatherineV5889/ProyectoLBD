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
        return tiendaDao.listarTiendas();
    }

    public void save(Tienda tienda) {
        if (tienda.getIdTienda() == null) {
            tiendaDao.agregarTienda(tienda.getNombre(), tienda.getDireccion(), tienda.getCiudad());
        } else {
            tiendaDao.modificarTienda(tienda.getIdTienda(), tienda.getNombre(), tienda.getDireccion(), tienda.getCiudad());
        }
    }
   
    public Tienda findById(Long id) {
        List<Tienda> tiendas = findAll();
        return tiendas.stream()
                .filter(t -> t.getIdTienda().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void eliminarTienda(Long id) {
        tiendaDao.eliminarTienda(id);
    } 
}

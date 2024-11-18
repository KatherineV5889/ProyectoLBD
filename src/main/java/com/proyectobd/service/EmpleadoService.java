package com.proyectobd.service;

import com.proyectobd.dao.EmpleadoDao;
import com.proyectobd.domain.Empleado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoDao empleadoDao;

    public List<Empleado> findAll() {
        return empleadoDao.findAll();
    }

    public Empleado findById(Long id) {
        return empleadoDao.findById(id).orElse(null);
    }

    public void save(Empleado empleado) {
        empleadoDao.save(empleado);
    }

    public void eliminarEmpleado(Long id) {
        empleadoDao.deleteById(id);
    }
}
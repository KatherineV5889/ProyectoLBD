package com.proyectobd.service;

import com.proyectobd.dao.EmpleadoDao;
import com.proyectobd.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoDao empleadoDao;

    public List<Empleado> listarEmpleados() {
        return empleadoDao.listarEmpleados();
    }

    public void guardarEmpleado(Empleado empleado) {
        if (empleado.getIdEmpleados() == null) {
            empleadoDao.agregarEmpleado(empleado);
        } else {
            empleadoDao.modificarEmpleado(empleado);
        }
    }

    public void eliminarEmpleado(Long id) {
        empleadoDao.eliminarEmpleado(id);
    }
}

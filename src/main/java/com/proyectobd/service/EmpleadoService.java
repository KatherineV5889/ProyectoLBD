package com.proyectobd.service;

import com.proyectobd.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.proyectobd.dao.EmpleadoDao;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoDao empleadosDao;

    // Método para obtener todos los empleados
    public List<Empleado> getAllEmpleados() {
        return empleadosDao.findAll();
    }

    // Método para obtener un empleado por ID
    public Optional<Empleado> getEmpleadoById(Long id) {
        return empleadosDao.findById(id);
    }

    // Método para guardar un nuevo empleado o actualizar uno existente
    public Empleado saveEmpleado(Empleado empleado) {
        return empleadosDao.save(empleado);
    }

    // Método para eliminar un empleado por ID
    public void deleteEmpleado(Long id) {
        empleadosDao.deleteById(id);
    }
}

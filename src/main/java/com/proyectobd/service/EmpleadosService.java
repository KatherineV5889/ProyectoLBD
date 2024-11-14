package com.proyectobd.service;

import com.proyectobd.dao.EmpleadosDao;
import com.proyectobd.domain.Empleados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadosService {

    @Autowired
    private EmpleadosDao empleadosDao;

    // Método para obtener todos los empleados
    public List<Empleados> getAllEmpleados() {
        return empleadosDao.findAll();
    }

    // Método para obtener un empleado por ID
    public Optional<Empleados> getEmpleadoById(Long id) {
        return empleadosDao.findById(id);
    }

    // Método para guardar un nuevo empleado o actualizar uno existente
    public Empleados saveEmpleado(Empleados empleado) {
        return empleadosDao.save(empleado);
    }

    // Método para eliminar un empleado por ID
    public void deleteEmpleado(Long id) {
        empleadosDao.deleteById(id);
    }
}

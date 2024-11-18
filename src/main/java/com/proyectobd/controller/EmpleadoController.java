package com.proyectobd.controller;

import com.proyectobd.domain.Empleado;
import com.proyectobd.service.EmpleadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.findAll();
        model.addAttribute("empleados", empleados);
        return "empleado/empleado";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Empleado nuevoEmpleado = new Empleado();
        model.addAttribute("empleado", nuevoEmpleado);
        return "empleado/agregarEmpleado";
    }

    @GetMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable("id") Long id, Model model) {
        Empleado empleado = empleadoService.findById(id);
        model.addAttribute("empleado", empleado);
        return "empleado/editarEmpleado";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
        empleadoService.save(empleado);
        System.out.println(empleado.toString());
        return "redirect:/empleado";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleado";
    }
}


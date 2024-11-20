package com.proyectobd.controller;

import com.proyectobd.domain.Empleado;
import com.proyectobd.service.EmpleadoService;
import com.proyectobd.service.TiendaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private TiendaService tiendaService; 

    @GetMapping
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.findAll();
        model.addAttribute("empleados", empleados != null ? empleados : List.of());
        return "empleado/empleado";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Empleado nuevoEmpleado = new Empleado();
        model.addAttribute("empleado", nuevoEmpleado);
        model.addAttribute("tiendas", tiendaService.findAll()); 
        return "empleado/agregarEmpleado";
    }

    @GetMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable("id") Long id, Model model) {
        Empleado empleado = empleadoService.findById(id);
        model.addAttribute("empleado", empleado);
        model.addAttribute("tiendas", tiendaService.findAll()); 
        return "empleado/editarEmpleado";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
        empleadoService.save(empleado);
        return "redirect:/empleado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleado";
    }
}

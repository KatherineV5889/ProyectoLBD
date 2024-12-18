package com.proyectobd.controller;

import com.proyectobd.domain.Empleado;
import com.proyectobd.service.EmpleadoService;
import com.proyectobd.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private TiendaService tiendaService;

    @GetMapping
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        model.addAttribute("empleados", empleados);
        return "empleado/empleado";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("tiendas", tiendaService.findAll());
        return "empleado/agregarEmpleado";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/empleado";
    }

    @GetMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable("id") Long id, Model model) {
        Empleado empleado = empleadoService.listarEmpleados().stream()
                .filter(e -> e.getIdEmpleados().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("empleado", empleado);
        model.addAttribute("tiendas", tiendaService.findAll());
        return "empleado/editarEmpleado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleado";
    }
}
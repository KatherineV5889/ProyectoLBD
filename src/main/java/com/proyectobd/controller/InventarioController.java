package com.proyectobd.controller;

import com.proyectobd.domain.Inventario;
import com.proyectobd.service.InventarioService;
import com.proyectobd.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private TiendaService tiendaService;

    @GetMapping
    public String listarInventarios(Model model) {
        List<Inventario> inventarios = inventarioService.listarInventarios();
        model.addAttribute("inventarios", inventarios);
        return "inventario/inventario"; // Ruta del template Thymeleaf
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Inventario inventario = new Inventario();
        model.addAttribute("inventario", inventario);
        model.addAttribute("tiendas", tiendaService.findAll());
        return "inventario/agregarInventario";
    }

    @PostMapping("/guardar")
    public String guardarInventario(@ModelAttribute("inventario") Inventario inventario) {
        inventarioService.guardarInventario(inventario);
        return "redirect:/inventario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Inventario inventario = inventarioService.listarInventarios().stream()
                .filter(i -> i.getIdInventario().equals(id))
                .findFirst()
                .orElse(null);

        if (inventario == null) {
            throw new IllegalArgumentException("Inventario no encontrado con ID: " + id);
        }

        model.addAttribute("inventario", inventario);
        model.addAttribute("tiendas", tiendaService.findAll());
        return "inventario/editarInventario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarInventario(@PathVariable("id") Long id) {
        inventarioService.eliminarInventario(id);
        return "redirect:/inventario";
    }
}
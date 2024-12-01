package com.proyectobd.controller;

import com.proyectobd.domain.Inventario;
import com.proyectobd.service.InventarioService;
import com.proyectobd.service.TiendaService;
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
@RequestMapping("/inventario")
public class InventarioController {
    
    @Autowired
    private InventarioService inventarioService;
    
    @Autowired
    private TiendaService tiendaService;     
    
    @GetMapping
    public String listarInventarios(Model model) {
        List<Inventario> inventarios = inventarioService.findAll();
        model.addAttribute("inventarios", inventarios);
        return "inventario/inventario";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Inventario nuevoInventario = new Inventario();
        model.addAttribute("inventario", nuevoInventario);
        model.addAttribute("tiendas", tiendaService.findAll()); 
        return "inventario/agregarInventario";
    }

    @GetMapping("/editar/{id}")
    public String editarInventario(@PathVariable("id") Long id, Model model) {
        Inventario inventario = inventarioService.findById(id);
        model.addAttribute("inventario", inventario);
        model.addAttribute("tiendas", tiendaService.findAll());
        return "inventario/editarInventario";
    }

    @PostMapping("/guardar")
    public String guardarInventario(@ModelAttribute("inventario") Inventario inventario) {
        inventarioService.save(inventario);
        System.out.println(inventario.toString());
        return "redirect:/inventario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarInventario(@PathVariable("id") Long id) {
        inventarioService.eliminarInventario(id);
        return "redirect:/inventario";
    }
    
    
}

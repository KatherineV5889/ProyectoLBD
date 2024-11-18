
package com.proyectobd.controller;

import com.proyectobd.domain.Venta;
import com.proyectobd.service.VentaService;
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
@RequestMapping("/venta")
public class VentaController {
    
    @Autowired
    private VentaService ventaService;
    
    @GetMapping
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaService.findAll();
        model.addAttribute("ventas", ventas); 
        return "venta/venta";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Venta nuevaVenta = new Venta();
        model.addAttribute("venta", nuevaVenta);
        return "venta/agregarVenta";
    }

    @GetMapping("/editar/{id}")
    public String editarVenta(@PathVariable("id") Long id, Model model) {
        Venta venta = ventaService.findById(id);
        model.addAttribute("venta", venta);
        return "venta/editarVenta";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute("venta") Venta venta) {
        ventaService.save(venta);
        System.out.println(venta.toString());
        return "redirect:/venta";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable("id") Long id) {
        ventaService.eliminarVenta(id);
        return "redirect:/venta";
    }
    
}
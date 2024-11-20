package com.proyectobd.controller;

import com.proyectobd.domain.Venta;
import com.proyectobd.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaService.listarTodas();
        model.addAttribute("ventas", ventas);
        return "venta/listar"; 
    }

    @GetMapping("/agregar")
    public String agregarVenta(Model model) {
        model.addAttribute("venta", new Venta());
        return "venta/agregar"; 
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute Venta venta) {
        ventaService.guardar(venta);
        return "redirect:/venta";
    }

    @GetMapping("/editar/{id}")
    public String editarVenta(@PathVariable Long id, Model model) {
        Venta venta = ventaService.buscarPorId(id).orElse(null);
        model.addAttribute("venta", venta);
        return "venta/editar"; 
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.eliminar(id);
        return "redirect:/venta";
    }
}

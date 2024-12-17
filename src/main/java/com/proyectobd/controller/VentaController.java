package com.proyectobd.controller;

import com.proyectobd.domain.Venta;
import com.proyectobd.service.VentaService;
import com.proyectobd.service.TiendaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

  
   
  

  

    @Autowired
    private TiendaService tiendaService;

    @GetMapping
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaService.findAll();
        model.addAttribute("ventas", ventas != null ? ventas : List.of());
        return "venta/venta";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Venta nuevaVenta = new Venta();
        model.addAttribute("venta", nuevaVenta);
        
       
        
        model.addAttribute("tiendas", tiendaService.findAll());
        return "venta/agregarVenta";
    }

    @GetMapping("/editar/{id}")
    public String editarVenta(@PathVariable("id") Long id, Model model) {
        Venta venta = ventaService.findById(id);
        model.addAttribute("venta", venta);
       
        
      
        model.addAttribute("tiendas", tiendaService.findAll());
        return "venta/editarVenta";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute("venta") Venta venta) {
        ventaService.save(venta);
        return "redirect:/venta";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable("id") Long id) {
        ventaService.eliminarVenta(id);
        return "redirect:/venta";
    }
}

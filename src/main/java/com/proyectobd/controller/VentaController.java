package com.proyectobd.controller;

import com.proyectobd.domain.Venta;
import com.proyectobd.service.VentaService;
import com.proyectobd.service.ClienteService;
import com.proyectobd.service.EmpleadoService;
import com.proyectobd.service.ProductoService;
import com.proyectobd.service.TiendaService;
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

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private TiendaService tiendaService;

   
    @GetMapping
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaService.listarVentas();
        model.addAttribute("ventas", ventas);
        return "venta/venta";
    }

   
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("tiendas", tiendaService.findAll());
        return "venta/agregarVenta";
    }

    
    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute("venta") Venta venta) {
        ventaService.guardarVenta(venta);
        return "redirect:/venta";
    }

    
    @GetMapping("/editar/{id}")
    public String editarVenta(@PathVariable("id") Long id, Model model) {
        
        Venta venta = ventaService.listarVentas().stream()
                .filter(v -> v.getIdVentas().equals(id))
                .findFirst()
                .orElse(null);

        
        if (venta == null) {
            throw new IllegalArgumentException("Venta no encontrada con ID: " + id);
        }

        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("tiendas", tiendaService.findAll());

        return "venta/editarVenta"; 
    }

   
    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable("id") Long id) {
        ventaService.eliminarVenta(id);
        return "redirect:/venta";
    }
}
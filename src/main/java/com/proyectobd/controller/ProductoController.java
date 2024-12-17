package com.proyectobd.controller;

import com.proyectobd.domain.Producto;
import com.proyectobd.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    
    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.listarProductos();
        model.addAttribute("productos", productos != null ? productos : List.of());
        return "producto/producto"; 
    }

    
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Producto nuevoProducto = new Producto();
        model.addAttribute("producto", nuevoProducto);
        return "producto/agregarProducto"; 
    }

    
    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.buscarPorId(id);
        model.addAttribute("producto", producto);
        return "producto/editarProducto"; 
    }

    
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        if (producto.getIdProducto() == null) {
            productoService.agregarProducto(producto);
        } else {
            productoService.modificarProducto(producto);
        }
        return "redirect:/producto"; 
    }

    
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/producto"; 
    }
}

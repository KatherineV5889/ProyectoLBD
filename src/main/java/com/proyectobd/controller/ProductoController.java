package com.proyectobd.controller;

import com.proyectobd.domain.Producto;
import com.proyectobd.service.CategoriaService;
import com.proyectobd.service.ProductoService;
import com.proyectobd.service.ProveedorService;
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

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorService proveedorService;

    
    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.listarProductos();
        model.addAttribute("productos", productos);
        return "producto/producto";
    }

    
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll()); // Lista de categorías
        model.addAttribute("proveedores", proveedorService.findAll()); // Lista de proveedores
        return "producto/agregarProducto";
    }

    /**
     * Guardar un producto (nuevo o editado)
     */
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/producto";
    }

    /**
     * Mostrar formulario para editar un producto
     */
    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.listarProductos().stream()
                .filter(p -> p.getIdProducto().equals(id))
                .findFirst()
                .orElse(null);

        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + id);
        }

        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.findAll()); // Lista de categorías
        model.addAttribute("proveedores", proveedorService.findAll()); // Lista de proveedores
        return "producto/editarProducto";
    }

    /**
     * Eliminar un producto
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/producto";
    }
}

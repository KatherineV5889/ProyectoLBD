package com.proyectobd.controller;

import com.proyectobd.domain.Producto;
import com.proyectobd.service.ProductoService;
import com.proyectobd.domain.Categoria;
import com.proyectobd.service.CategoriaService;
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
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos != null ? productos : List.of());
        return "producto/producto";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Producto nuevoProducto = new Producto();
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("producto", nuevoProducto);
        model.addAttribute("categorias", categorias);
        return "producto/agregarProducto";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.findById(id);
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);
        return "producto/editarProducto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoService.save(producto);
        return "redirect:/producto";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/producto";
    }
}

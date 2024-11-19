package com.proyectobd.controller;

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
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaService.findAll();       
        model.addAttribute("categorias", categorias != null ? categorias : List.of()); 
        return "categoria/categoria";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Categoria nuevoCategoria = new Categoria();
        model.addAttribute("categoria", nuevoCategoria);
        return "categoria/agregarCategoria";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable("id") Long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        model.addAttribute("categoria", categoria);
        return "categoria/editarCategoria";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.save(categoria);
        System.out.println(categoria.toString());
        return "redirect:/categoria";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categoria";

    }
    
}

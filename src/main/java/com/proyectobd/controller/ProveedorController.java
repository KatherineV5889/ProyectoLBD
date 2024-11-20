package com.proyectobd.controller;

import com.proyectobd.domain.Proveedor;
import com.proyectobd.service.ProveedorService;
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
@RequestMapping("/proveedor")
public class ProveedorController {
    
    @Autowired
    private ProveedorService proveedorService;
    
    @GetMapping
    public String listarProveedores(Model model) {
        List<Proveedor> proveedores = proveedorService.findAll();
        model.addAttribute("proveedores", proveedores); 
        return "proveedor/proveedor";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Proveedor nuevoProveedor = new Proveedor();
        model.addAttribute("proveedor", nuevoProveedor);
        return "proveedor/agregarProveedor";
    }

    @GetMapping("/editar/{id}")
    public String editarProveedor(@PathVariable("id") Long id, Model model) {
        Proveedor proveedor = proveedorService.findById(id);
        model.addAttribute("proveedor", proveedor);
        return "proveedor/editarProveedor";
    }

    @PostMapping("/guardar")
    public String guardarProveedor(@ModelAttribute("proveedor") Proveedor proveedor) {
        proveedorService.save(proveedor);
        System.out.println(proveedor.toString());
        return "redirect:/proveedor";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable("id") Long id) {
        proveedorService.eliminarProveedor(id);
        return "redirect:/proveedor";

    }
    
}

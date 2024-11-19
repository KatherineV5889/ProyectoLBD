package com.proyectobd.controller;

import com.proyectobd.domain.Tienda;
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
@RequestMapping("/tienda")
public class TiendaController {
    
    @Autowired
    private TiendaService tiendaService;
    
    @GetMapping
    public String listarTiendas(Model model) {
        List<Tienda> tiendas = tiendaService.findAll();
        model.addAttribute("tiendas", tiendas);
        return "tienda/tienda";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Tienda nuevoTienda = new Tienda();
        model.addAttribute("tienda", nuevoTienda);
        return "tienda/agregarTienda";
    }

    @GetMapping("/editar/{id}")
    public String editarTienda(@PathVariable("id") Long id, Model model) {
        Tienda tienda = tiendaService.findById(id);
        model.addAttribute("tienda", tienda);
        return "tienda/editarTienda";
    }

    @PostMapping("/guardar")
    public String guardarTienda(@ModelAttribute("tienda") Tienda tienda) {
        tiendaService.save(tienda);
        System.out.println(tienda.toString());
        return "redirect:/tienda";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTienda(@PathVariable("id") Long id) {
        tiendaService.eliminarTienda(id);
        return "redirect:/tienda";

    }
}

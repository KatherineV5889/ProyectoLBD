package com.proyectobd.controller;

import com.proyectobd.domain.Pedido;
import com.proyectobd.service.PedidoService;
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
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
     @GetMapping
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);
        return "pedido/pedido";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Pedido nuevoPedido = new Pedido();
        model.addAttribute("pedido", nuevoPedido);
        return "pedido/agregarPedido";
    }

    @GetMapping("/editar/{id}")
    public String editarPedido(@PathVariable("id") Long id, Model model) {
        Pedido pedido = pedidoService.findById(id);
        model.addAttribute("pedido", pedido);
        return "pedido/editarPedido";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute("pedido") Pedido pedido) {
        pedidoService.save(pedido);
        System.out.println(pedido.toString());
        return "redirect:/pedido";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable("id") Long id) {
        pedidoService.eliminarPedido(id);
        return "redirect:/pedido";

    }
    
}

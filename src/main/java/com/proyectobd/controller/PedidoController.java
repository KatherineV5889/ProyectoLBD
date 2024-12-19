package com.proyectobd.controller;

import com.proyectobd.domain.Pedido;
import com.proyectobd.service.PedidoService;
import com.proyectobd.service.ProductoService;
import com.proyectobd.service.ProveedorService;
import com.proyectobd.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private TiendaService tiendaService;

    @GetMapping
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        model.addAttribute("pedidos", pedidos);
        return "pedido/pedido";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Pedido pedido = new Pedido();
        model.addAttribute("pedido", pedido);
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("proveedores", proveedorService.findAll());
        model.addAttribute("tiendas", tiendaService.findAll());
        return "pedido/agregarPedido";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute("pedido") Pedido pedido) {
        pedidoService.guardarPedido(pedido);
        return "redirect:/pedido";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Pedido pedido = pedidoService.listarPedidos().stream()
                .filter(p -> p.getIdPedido().equals(id))
                .findFirst()
                .orElse(null);

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no encontrado con ID: " + id);
        }

        model.addAttribute("pedido", pedido);
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("proveedores", proveedorService.findAll());
        model.addAttribute("tiendas", tiendaService.findAll());
        return "pedido/editarPedido";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable("id") Long id) {
        pedidoService.eliminarPedido(id);
        return "redirect:/pedido";
    }
}

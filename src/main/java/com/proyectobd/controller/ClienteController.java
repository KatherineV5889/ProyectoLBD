package com.proyectobd.controller;

import com.proyectobd.domain.Cliente;
import com.proyectobd.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.listarClientes();
        model.addAttribute("clientes", clientes != null ? clientes : List.of());
        return "cliente/cliente"; // Vista para listar clientes
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        Cliente nuevoCliente = new Cliente();
        model.addAttribute("cliente", nuevoCliente);
        return "cliente/agregarCliente"; // Vista para agregar cliente
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente/editarCliente"; // Vista para editar cliente
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
        if (cliente.getIdCliente() == null) {
            clienteService.agregarCliente(cliente);
        } else {
            clienteService.modificarCliente(cliente);
        }
        return "redirect:/cliente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/cliente";
    }
}



package com.proyectobd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ClienteController {
    
    @RequestMapping("/clientes")
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "clientes";
    }
    
}

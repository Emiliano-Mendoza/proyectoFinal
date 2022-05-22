package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/usuario")
public class UsuarioController {
	
	
	@GetMapping("/administrar")
	public String administrarUsuario(Model model) {
						
		return "/views/usuario/AdministrarUsuario";
	}
}

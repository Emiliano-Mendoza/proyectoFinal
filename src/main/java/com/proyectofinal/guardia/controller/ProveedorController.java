package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/proveedor")
public class ProveedorController {
	
	
	@GetMapping("/administrar")
	public String administrarProveedor(Model model) {
						
		return "/views/proveedor/AdministrarProveedor";
	}
}

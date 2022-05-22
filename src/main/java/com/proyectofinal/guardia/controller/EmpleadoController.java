package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/empleado")
public class EmpleadoController {
	
	@GetMapping("/administrar")
	public String administrarEmpleados(Model model) {
						
		return "/views/empleados/AdministrarEmpleado";
	}
	
}

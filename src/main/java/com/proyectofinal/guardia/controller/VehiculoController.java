package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/vehiculo")
public class VehiculoController {
	
	
	@GetMapping("/administrar")
	public String administrarVehiculo(Model model) {
						
		return "/views/vehiculo/AdministrarVehiculo";
	}
	
}

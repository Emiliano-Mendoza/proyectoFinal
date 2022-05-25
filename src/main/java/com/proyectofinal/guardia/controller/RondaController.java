package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/ronda")
public class RondaController {
	
	
	@GetMapping
	public String nuevoRegistro(Model model) {
						
		return "/views/rondas/RegistrarAcontecimientoDeRonda";
	}
	
	
	@GetMapping("/registros")
	public String verRegistrosDeAcotecimientos(Model model) {
						
		return "/views/rondas/VerRegistrosDeRondas";
	}

	
}

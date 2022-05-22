package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/evento")
public class EventoController {
	
	@GetMapping
	public String nuevoEvento(Model model) {
						
		return "/views/eventos/NotificarNuevoEvento";
	}
	
	@GetMapping("/ver-eventos")
	public String verEventos(Model model) {
						
		return "/views/eventos/verEventos";
	}
	
	@GetMapping("/ocurrencia")
	public String ocurrenciaDeEventos(Model model) {
						
		return "/views/eventos/RegistrarOcurrenciaDeEvento";
	}
}

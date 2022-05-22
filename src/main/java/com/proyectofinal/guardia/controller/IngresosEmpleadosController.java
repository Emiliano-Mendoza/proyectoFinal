package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/ingresos-empleado")
public class IngresosEmpleadosController {
	
	
	@GetMapping
	public String listarEmpleados(Model model) {
						
		return "/views/asistencias/RegistrarIngresoEmpleado";
	}
	
	@GetMapping("/egreso")
	public String listarAsistenciasYTransitos(Model model) {
						
		return "/views/asistencias/RegistrarEgresoEmpleado";
	}
	
}

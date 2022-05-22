package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/retiro-material")
public class RetiroMaterialController {
	
	
	@GetMapping("/autorizaciones")
	public String listarAutorizaciones(Model model) {
						
		return "/views/retirosMaterial/RegistrarRetiroMaterial";
	}
	
	@GetMapping("/nueva-autorizacion")
	public String listarEmpleadosYMateriales(Model model) {
						
		return "/views/retirosMaterial/AutorizarRetiroMaterial";
	}
	
	@GetMapping("/ver-retiros")
	public String verRetiros(Model model) {
						
		return "/views/retirosMaterial/VerRetirosDeMaterial";
	}
}

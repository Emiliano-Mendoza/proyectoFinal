package com.proyectofinal.guardia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectofinal.guardia.service.EmpleadoService;

@Controller
@RequestMapping("/views/retiro-material")
public class RetiroMaterialController {
	
	@Autowired
	private EmpleadoService empleadoServ;
	
	@GetMapping("/autorizaciones")
	public String listarAutorizaciones(Model model) {
						
		return "/views/retirosMaterial/RegistrarRetiroMaterial";
	}
	
	@GetMapping("/nueva-autorizacion")
	public String listarEmpleadosYMateriales(Model model) {
		
		model.addAttribute("listaEmpleados", empleadoServ.obtenerDisponibles());
		
		return "/views/retirosMaterial/AutorizarRetiroMaterial";
	}
	
	@GetMapping("/ver-retiros")
	public String verRetiros(Model model) {
						
		return "/views/retirosMaterial/VerRetirosDeMaterial";
	}
}

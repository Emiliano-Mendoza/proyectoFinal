package com.proyectofinal.guardia.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/views/distribucion")
public class DistribucionController {
	
	@GetMapping
	public String nuevaDistribucion(Model model) {
		
		
		return "/views/distribucion/CrearNuevaDistribucion";
	}
	
	
	@PostMapping("/crear")
	public String crearSector(@RequestBody List<Map<String, Object>> distribucion) {
						
		try {
			
			System.out.println(distribucion);

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "/views/distribucion/CrearNuevaDistribucion";
	}
}

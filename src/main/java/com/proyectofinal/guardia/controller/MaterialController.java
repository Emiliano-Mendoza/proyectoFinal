package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/material")
public class MaterialController {

	
	@GetMapping("/administrar")
	public String administrarMaterial(Model model) {
						
		return "/views/material/AdministrarMaterial";
	}
}

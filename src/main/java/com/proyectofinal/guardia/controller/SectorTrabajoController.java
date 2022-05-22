package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/sector-trabajo")
public class SectorTrabajoController {
	
	
	@GetMapping("/administrar")
	public String administrarSector(Model model) {
						
		return "/views/sectorTrabajo/AdministrarSectorTrabajo";
	}
}

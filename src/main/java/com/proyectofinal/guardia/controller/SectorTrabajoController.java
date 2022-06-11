package com.proyectofinal.guardia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectofinal.guardia.domain.SectorTrabajo;
import com.proyectofinal.guardia.service.EmpleadoService;
import com.proyectofinal.guardia.service.SectorTrabajoService;

@Controller
@RequestMapping("/views/sector-trabajo")
public class SectorTrabajoController {
	
	@Autowired
	private SectorTrabajoService sectorServ;
	
	@GetMapping("/administrar")
	public String administrarSector(Model model) {
						
		return "/views/sectorTrabajo/AdministrarSectorTrabajo";
	}
	
	
	@GetMapping
	public ResponseEntity<List<SectorTrabajo>> obtenerSectoresDeTrabajo(){

		
		return ResponseEntity.ok(sectorServ.obtenerDisponibles());
	}
}

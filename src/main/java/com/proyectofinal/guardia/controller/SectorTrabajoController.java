package com.proyectofinal.guardia.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectofinal.guardia.domain.SectorTrabajo;

import com.proyectofinal.guardia.service.SectorTrabajoService;

@Controller
@RequestMapping("/views/sector-trabajo")
public class SectorTrabajoController {
	
	@Autowired
	private SectorTrabajoService sectorServ;
	
	@GetMapping("/administrar")
	public String administrarSector(Model model) {
		
		List<SectorTrabajo> listaSectores = sectorServ.obtenerTodos();
		model.addAttribute("listaSectores", listaSectores);
		
		return "/views/sectorTrabajo/AdministrarSectorTrabajo";
	}
	
	@PostMapping("/administrar/crear")
	public String crearSector(@RequestBody Map<String, Object> sector) {
		
		
		
		try {
			
			System.out.println(sector);
			System.out.println(sector.get("sector"));
			System.out.println(sector.get("activo"));
			List<Map<String,Integer>> maps = (List<Map<String, Integer>>) sector.get("areas");
			maps.forEach(a -> {
				System.out.println(a.get("nombre"));
				System.out.println(a.get("descripcion"));
				System.out.println(a.get("activo"));
			});
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "/views/sectorTrabajo/AdministrarSectorTrabajo";
	}
	
	@PostMapping("/administrar/editar")
	public String editarSector(@RequestBody Map<String, Object> sector) {
		
		System.out.println(sector);		
		try {
			
						
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "/views/sectorTrabajo/AdministrarSectorTrabajo";
	}
	
	@GetMapping
	public ResponseEntity<List<SectorTrabajo>> obtenerSectoresDeTrabajo(){

		
		return ResponseEntity.ok(sectorServ.obtenerDisponibles());
	}
}

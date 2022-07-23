package com.proyectofinal.guardia.controller;



import java.util.ArrayList;
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

import com.proyectofinal.guardia.domain.AreaTrabajo;
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
			SectorTrabajo nuevoSector = new SectorTrabajo();
			nuevoSector.setSector(sector.get("sector").toString());
			nuevoSector.setActivo(sector.get("activo").toString().equals("1") ? true : false);
						
			List<Map<String,Object>> maps = (List<Map<String, Object>>) sector.get("areas");
			maps.forEach(a -> {
				AreaTrabajo nuevaArea = new AreaTrabajo();
				nuevaArea.setNombreArea(a.get("nombre").toString());
				nuevaArea.setDescripcion(a.get("descripcion").toString());
				nuevaArea.setActivo(true);
				nuevoSector.getAreas().add(nuevaArea);
				
			});
			sectorServ.crearSector(nuevoSector);
			
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

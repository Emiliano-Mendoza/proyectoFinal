package com.proyectofinal.guardia.controller;



import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
	
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@GetMapping("/administrar")
	public String administrarSector(Model model) {
		
		List<SectorTrabajo> listaSectores = sectorServ.obtenerTodos();
		model.addAttribute("listaSectores", listaSectores);
		
		return "/views/sectorTrabajo/AdministrarSectorTrabajo";
	}
	
	@PostMapping("/administrar/crear")
	public ResponseEntity<List<SectorTrabajo>> crearSector(@RequestBody Map<String, Object> sector) {
				
		try {
			SectorTrabajo nuevoSector = new SectorTrabajo();
			nuevoSector.setSector(sector.get("sector").toString());
			nuevoSector.setActivo(sector.get("activo").toString().equals("1") ? true : false);

			List<Map<String,Object>> maps = (List<Map<String, Object>>) sector.get("areas");
			maps.forEach(a -> {
				AreaTrabajo nuevaArea = new AreaTrabajo();
				nuevaArea.setNombreArea(a.get("nombre").toString());
				nuevaArea.setDescripcion(a.get("descripcion").toString());
				nuevaArea.setActivo(a.get("activo").toString().equals("1") ? true : false);
				
				Set<ConstraintViolation<AreaTrabajo>> violationsAreas = validator.validate(nuevaArea);
				if(violationsAreas.isEmpty()) {
					nuevoSector.getAreas().add(nuevaArea);
				}else {
					throw new RuntimeException("Datos area invalidos");
				}
			
			});
			
			//validar sector
			Set<ConstraintViolation<SectorTrabajo>> violationsSectores = validator.validate(nuevoSector);
			
			if(violationsSectores.isEmpty()) {
				sectorServ.crearSector(nuevoSector);
			}else {
				throw new Exception("Datos invalidos");
			}						
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		
		return ResponseEntity.ok(sectorServ.obtenerTodos());
	}
	
	@PostMapping("/administrar/editar")
	public ResponseEntity<List<SectorTrabajo>> editarSector(@RequestBody Map<String, Object> sector) {
		
			
		try {
			
			SectorTrabajo nuevoSector = new SectorTrabajo();
			nuevoSector.setIdSector(Integer.parseInt(sector.get("idSector").toString()));
			nuevoSector.setSector(sector.get("sector").toString());
			nuevoSector.setActivo(sector.get("activo").toString().equals("1") ? true : false);
			
			List<Map<String,Object>> maps = (List<Map<String, Object>>) sector.get("areas");
			maps.forEach(a -> {
				AreaTrabajo nuevaArea = new AreaTrabajo();
				nuevaArea.setIdArea(Integer.parseInt(a.get("idArea").toString()));
				nuevaArea.setNombreArea(a.get("nombre").toString());
				nuevaArea.setDescripcion(a.get("descripcion").toString());
				nuevaArea.setActivo(a.get("activo").toString().equals("1") ? true : false);

				Set<ConstraintViolation<AreaTrabajo>> violationsAreas = validator.validate(nuevaArea);
				if(violationsAreas.isEmpty()) {
					nuevoSector.getAreas().add(nuevaArea);
				}else {
					throw new RuntimeException("Datos area invalidos");
				}
				
			});
			
			//validar sector
			Set<ConstraintViolation<SectorTrabajo>> violationsSectores = validator.validate(nuevoSector);
			
			if(violationsSectores.isEmpty()) {
				sectorServ.crearSector(nuevoSector);
			}else {
				throw new Exception("Datos invalidos");
			}		
				
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		
		return ResponseEntity.ok(sectorServ.obtenerTodos());
	}
	
	@GetMapping
	public ResponseEntity<List<SectorTrabajo>> obtenerSectoresDeTrabajo(){
		
		return ResponseEntity.ok(sectorServ.obtenerDisponibles());
	}
}

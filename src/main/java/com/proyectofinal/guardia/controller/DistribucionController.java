package com.proyectofinal.guardia.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectofinal.guardia.domain.SectorTrabajo;
import com.proyectofinal.guardia.service.EmpleadoService;
import com.proyectofinal.guardia.service.SectorTrabajoService;


@Controller
@RequestMapping("/views/distribucion")
public class DistribucionController {
	
	@Autowired
	private SectorTrabajoService sectorServ;
	@Autowired
	private EmpleadoService empleadoServ;
	
	@GetMapping
	public String nuevaDistribucion(Model model) {
		
		List<SectorTrabajo> listaSectores = sectorServ.obtenerDisponibles()
				.stream()
				.filter(sector -> !sector.getAreas().isEmpty())
				.collect(Collectors.toList());
		model.addAttribute("listaSectores", listaSectores);
		model.addAttribute("listaEmpleados", empleadoServ.obtenerDisponibles());
		
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

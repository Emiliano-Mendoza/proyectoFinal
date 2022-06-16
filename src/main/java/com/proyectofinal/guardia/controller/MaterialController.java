package com.proyectofinal.guardia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectofinal.guardia.domain.Material;
import com.proyectofinal.guardia.service.MaterialService;

@Controller
@RequestMapping("/views/material")
public class MaterialController {
	
	@Autowired
	private MaterialService materialServ;
	
	@GetMapping("/administrar")
	public String administrarMaterial(Model model) {
						
		return "/views/material/AdministrarMaterial";
	}
	
	
	@GetMapping
	public ResponseEntity<List<Material>> listarMaterialesDisponibles(){
		
		return ResponseEntity.ok(materialServ.obtenerDisponibles());
	}
}

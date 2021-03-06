package com.proyectofinal.guardia.controller;


import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectofinal.guardia.domain.AutorizacionRetiroMaterial;
import com.proyectofinal.guardia.service.EmpleadoService;
import com.proyectofinal.guardia.service.RetiroMaterialService;

@Controller
@RequestMapping("/views/retiro-material")
public class RetiroMaterialController {
	
	@Autowired
	private RetiroMaterialService retiroMaterialServ;
	
	@Autowired
	private EmpleadoService empleadoServ;
	
	@GetMapping("/autorizaciones")
	public String mostrarAutorizaciones(Model model) {
		
		model.addAttribute("listaAutorizaciones", retiroMaterialServ.obtenerAutorizacionesActivas());
		
		return "/views/retirosMaterial/RegistrarRetiroMaterial";
	}
	
	@GetMapping("/nueva-autorizacion")
	public String listarEmpleadosYMateriales(Model model) {
		
		model.addAttribute("listaEmpleados", empleadoServ.obtenerDisponibles());
		
		return "/views/retirosMaterial/AutorizarRetiroMaterial";
	}
	
	@GetMapping("/ver-retiros")
	public String verRetiros(Model model) {
		
		model.addAttribute("listaAutorizaciones", retiroMaterialServ.obtenerAutorizaciones());
		
		return "/views/retirosMaterial/VerRetirosDeMaterial";
	}
	
	@PostMapping("/nueva-autorizacion/crear")
	public String nuevaAutorizacionDeRetiro(@RequestParam(name = "desc") String desc,
											@RequestParam(name = "fechaLimite") String fecha,
											@RequestParam(name = "nroLegajo") int nroLegajo,
											@RequestParam(name = "materiales") List<Integer> materiales,
											RedirectAttributes atributos){
				
		try {

			if(materiales.isEmpty()) throw new Exception("No se pudo crear la nueva autorizaci??n. Lista de materiales vac??a.");
			
			retiroMaterialServ.crearAutorizacion(nroLegajo, materiales, fecha, desc);
						
			
		} catch (Exception e) {
			atributos.addFlashAttribute("error", e.getMessage());
			return "redirect:/views/retiro-material/nueva-autorizacion";
		}
		
		atributos.addFlashAttribute("success", "Autorizaci??n de retiro creada exitosamente!");
		return "redirect:/views/retiro-material/nueva-autorizacion";
	}
	
	@PostMapping("/retiro/{idAutorizacion}")
	public String registroDeRetiro(@PathVariable("idAutorizacion") int idAutorizacion, 
			@RequestParam(name = "observacionGuardia") String observacionGuardia,
			@RequestParam(name = "planta") int planta,
			RedirectAttributes atributos) {
				
		try {
			
			String plantaAux = "No definida";
			
			if(planta == 1) plantaAux = "Av. Facundo Zuvir??a 4740 - Planta I";
			if(planta == 2) plantaAux = "Av. Pe??aloza 5750 - Planta II";
			
			
			retiroMaterialServ.registrarRetiro(idAutorizacion, new Date(), observacionGuardia, plantaAux);
			
		}catch(Exception e) {
			atributos.addFlashAttribute("error", e.getMessage());
			return "redirect:/views/retiro-material/autorizaciones";
		}
		
		atributos.addFlashAttribute("success", "Retiro de material registrado con ??xito!");
		return "redirect:/views/retiro-material/autorizaciones";
	}
	
	@GetMapping("/autorizaciones/listar")
	public ResponseEntity<List<AutorizacionRetiroMaterial>> listarAutorizaciones(){
		
		return ResponseEntity.ok(retiroMaterialServ.obtenerAutorizacionesActivas());
		
	}
	
	@GetMapping("/autorizaciones/filtrar")
	public ResponseEntity<List<AutorizacionRetiroMaterial>> filtrarAutorizaciones(@RequestParam(name = "date_range") String date_range,
			@RequestParam(name = "date_range2") String date_range2,
			@RequestParam(name = "nroLegajo", required = false) int nroLegajo,
			@RequestParam(name = "idAutorizante", required = false) int idAutorizante,
			@RequestParam(name = "idGuardia", required = false) int idGuardia,
			@RequestParam(name = "idMaterial", required = false) int idMaterial){
		
		
		if (date_range.indexOf(" - ") != -1 && date_range2.indexOf(" - ") != -1){
			String[] parts = date_range.split(" - ");
			String[] parts2 = date_range2.split(" - ");
			return ResponseEntity.ok(retiroMaterialServ.filtrarAutorizaciones(parts[0], parts[1], parts2[0], parts2[1], nroLegajo, idAutorizante, idGuardia, idMaterial));
		}
		else if (date_range.indexOf(" - ") != -1) {			
			String[] parts = date_range.split(" - ");			
			return ResponseEntity.ok(retiroMaterialServ.filtrarAutorizaciones(parts[0], parts[1], null, null, nroLegajo, idAutorizante, idGuardia, idMaterial));
			
		}else if (date_range2.indexOf(" - ") != -1) {
			String[] parts2 = date_range2.split(" - ");
			return ResponseEntity.ok(retiroMaterialServ.filtrarAutorizaciones(null, null, parts2[0], parts2[1], nroLegajo, idAutorizante, idGuardia, idMaterial));
			
		}
				
		return ResponseEntity.ok(retiroMaterialServ.filtrarAutorizaciones(null, null, null, null, nroLegajo, idAutorizante, idGuardia, idMaterial));
		
	}
	
}

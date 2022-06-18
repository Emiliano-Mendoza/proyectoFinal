package com.proyectofinal.guardia.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

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
						
		return "/views/retirosMaterial/VerRetirosDeMaterial";
	}
	
	@PostMapping("/nueva-autorizacion/crear")
	public String nuevaAutorizacionDeRetiro(@RequestParam(name = "desc") String desc,
											@RequestParam(name = "fechaLimite") String fecha,
											@RequestParam(name = "nroLegajo") int nroLegajo,
											@RequestParam(name = "materiales") List<Integer> materiales,
											RedirectAttributes atributos){
				
		try {

			if(materiales.isEmpty()) throw new Exception("No se pudo crear la nueva autorización. Lista de materiales vacía.");
			
			retiroMaterialServ.crearAutorizacion(nroLegajo, materiales, fecha, desc);
						
			
		} catch (Exception e) {
			atributos.addFlashAttribute("error", e.getMessage());
			return "redirect:/views/retiro-material/nueva-autorizacion";
		}
		
		atributos.addFlashAttribute("success", "Autorización de retiro creada exitosamente!");
		return "redirect:/views/retiro-material/nueva-autorizacion";
	}
	
	@PostMapping("/retiro/{idAutorizacion}")
	public String registroDeRetiro(@PathVariable("idAutorizacion") int idAutorizacion, 
			@RequestParam(name = "observacionGuardia") String observacionGuardia,
			@RequestParam(name = "planta") String planta,
			RedirectAttributes atributos) {
				
		try {
						
			retiroMaterialServ.registrarRetiro(idAutorizacion, new Date(), observacionGuardia, planta);
			
		}catch(Exception e) {
			atributos.addFlashAttribute("error", e.getMessage());
			return "redirect:/views/retiro-material/autorizaciones";
		}
		
		atributos.addFlashAttribute("success", "Retiro de material registrado con éxito!");
		return "redirect:/views/retiro-material/autorizaciones";
	}
	
	@GetMapping("/autorizaciones/listar")
	public ResponseEntity<List<AutorizacionRetiroMaterial>> listarAutorizaciones(){
		
		return ResponseEntity.ok(retiroMaterialServ.obtenerAutorizacionesActivas());
		
	}
	
	
	
}

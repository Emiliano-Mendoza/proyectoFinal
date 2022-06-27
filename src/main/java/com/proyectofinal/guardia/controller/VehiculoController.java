package com.proyectofinal.guardia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectofinal.guardia.domain.Vehiculo;
import com.proyectofinal.guardia.service.VehiculoService;

@Controller
@RequestMapping("/views/vehiculo")
public class VehiculoController {

	@Autowired
	private VehiculoService vehiculoServ;

	@GetMapping("/administrar")
	public String administrarVehiculo(Model model) {

		model.addAttribute("listaVehiculos", vehiculoServ.obtenerTodos());
		model.addAttribute("vehiculo", new Vehiculo());

		return "/views/vehiculo/AdministrarVehiculo";
	}

	@PostMapping("/crear")
	public String crearVehiculo(@Valid @ModelAttribute Vehiculo vehiculo, BindingResult result,
			 Model model, RedirectAttributes atributos) {
		
		
		if (result.hasErrors()) {
			
			atributos.addFlashAttribute("error", "Datos inválidos. No se pudo crear el vehiculo.");
			return "redirect:/views/vehiculo/administrar";
			
		}else {
			
			try {
				vehiculoServ.crearVehiculo(vehiculo);
			} catch (Exception e) {
				atributos.addFlashAttribute("error", "No se pudo crear el vehiculo");
				return "redirect:/views/vehiculo/administrar";
			}
		}

		atributos.addFlashAttribute("success", "Vehiculo creado exitosamente!");
		return "redirect:/views/vehiculo/administrar";
	}
	
	@PostMapping("/editar")
	public String editarVehiculo(@Valid @ModelAttribute Vehiculo vehiculo, BindingResult result,
			 Model model, RedirectAttributes atributos) {
		
		if (result.hasErrors()) {
			
			atributos.addFlashAttribute("error", "Datos inválidos. No se pudo editar el vehiculo.");
			return "redirect:/views/vehiculo/administrar";
			
		}else {
			
			
			try {
				vehiculoServ.editarVehiculo(vehiculo);
			} catch (Exception e) {
				atributos.addFlashAttribute("error", "No se pudo editar el vehiculo");
				return "redirect:/views/vehiculo/administrar";
			}
		}

		atributos.addFlashAttribute("success", "Vehiculo editado exitosamente!");
		return "redirect:/views/vehiculo/administrar";
	}
	
	
	@GetMapping
	public ResponseEntity<List<Vehiculo>> listarVehiculosDisponibles(){
		
		return ResponseEntity.ok(vehiculoServ.obtenerDisponibles());
	}
	
	@GetMapping("/listar-todos")
	public ResponseEntity<List<Vehiculo>> listarTodosVehiculos(){
		
		return ResponseEntity.ok(vehiculoServ.obtenerTodos());
	}
}

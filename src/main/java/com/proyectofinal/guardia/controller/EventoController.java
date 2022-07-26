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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectofinal.guardia.domain.Evento;
import com.proyectofinal.guardia.service.EventoService;

@Controller
@RequestMapping("/views/evento")
public class EventoController {
	
	@Autowired
	private EventoService eventoServ;
	
	@GetMapping
	public String nuevoEvento(Model model) {
				
		model.addAttribute("listaEventos", eventoServ.obtenerActivosUsuario());
		model.addAttribute("evento", new Evento());
		
		return "/views/eventos/NotificarNuevoEvento";
	}
	
	@PostMapping("/crear")
	public String crearEvento(@Valid @ModelAttribute Evento evento,
			@RequestParam(name = "fecha") String fecha,
			BindingResult result,
			RedirectAttributes atributos) {
		
		if (result.hasErrors()) {
			
			atributos.addFlashAttribute("error", "Datos inválidos. No se pudo crear el evento.");
			return "redirect:/views/evento";
			
		}else {			
			try {
				eventoServ.crearEvento(evento, fecha);
			} catch (Exception e) {
				atributos.addFlashAttribute("error", "No se pudo crear el evento");
				return "redirect:/views/evento";
			}
		}

		atributos.addFlashAttribute("success", "Vehiculo creado exitosamente!");
		return "redirect:/views/evento";
	}
	
	@PostMapping("/editar")
	public String editarEvento(@Valid @ModelAttribute Evento evento, BindingResult result,
			 RedirectAttributes atributos) {
				
		if (result.hasErrors()) {
			
			atributos.addFlashAttribute("error", "Datos inválidos. No se pudo editar el evento.");
			return "redirect:/views/evento";
			
		}else {			
			try {
				
			} catch (Exception e) {
				atributos.addFlashAttribute("error", "No se pudo editar el evento");
				return "redirect:/views/evento";
			}
		}

		atributos.addFlashAttribute("success", "Evento editado exitosamente!");
		return "redirect:/views/evento";
	}
	
	@GetMapping("/ver-eventos")
	public String verEventos(Model model) {
						
		return "/views/eventos/verEventos";
	}
	
	@GetMapping("/ocurrencia")
	public String ocurrenciaDeEventos(Model model) {
						
		return "/views/eventos/RegistrarOcurrenciaDeEvento";
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Evento>> listarEventos(){
		
		return ResponseEntity.ok(eventoServ.obtenerActivos());
		
	}
}

package com.proyectofinal.guardia.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostMapping("/editar/{idEvento}")
	public String editarEvento(@PathVariable("idEvento") int idEvento,
			   @RequestParam(name = "descripcion") String descripcion,
			   @RequestParam(name = "fechaEvento") String fechaEvento,
			 RedirectAttributes atributos) {
		
		try {
			Evento evento = eventoServ.obtenerPorId(idEvento).orElseThrow();
			evento.setDescripcion(descripcion);
			eventoServ.crearEvento(evento, fechaEvento);
			
		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo editar el evento");
			return "redirect:/views/evento";
		}
		
		atributos.addFlashAttribute("success", "Evento editado exitosamente!");
		return "redirect:/views/evento";
	}
	
	@PostMapping("/cancelar/{idEvento}")
	public String cancelarEvento(@PathVariable("idEvento") int idEvento,
			   @RequestParam(name = "motivoCancelacion") String motivoCancelacion,
			 RedirectAttributes atributos) {
		
		try {
			Evento evento = eventoServ.obtenerPorId(idEvento).orElseThrow();
			evento.setMotivoCancelacion(motivoCancelacion);
			evento.setCancelado(true);
			eventoServ.editarEvento(evento);
			
		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo cancelar el evento");
			return "redirect:/views/evento";
		}
		
		atributos.addFlashAttribute("success", "Evento cancelado exitosamente!");
		return "redirect:/views/evento";
	}
	
	@GetMapping("/ver-eventos")
	public String verEventos(Model model) {
		
		model.addAttribute("listaEventos", eventoServ.obtenerTodos());
	
		return "/views/eventos/verEventos";
	}
	
	@GetMapping("/ocurrencia")
	public String ocurrenciaDeEventos(Model model) {
		
		model.addAttribute("listaEventos", eventoServ.obtenerActivos());
		
		return "/views/eventos/RegistrarOcurrenciaDeEvento";
	}
	
	@PostMapping("/ocurrencia/{idEvento}")
	public String ocurrenciaEvento(@PathVariable("idEvento") int idEvento,
			   @RequestParam(name = "observacionGuardia") String observacionGuardia,
			 RedirectAttributes atributos) {
		
		try {
			Evento evento = eventoServ.obtenerPorId(idEvento).orElseThrow();
			evento.setObservacion(observacionGuardia);
			evento.setOcurrencia(true);
			eventoServ.marcarOcurrenciaEvento(evento);
			
		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo registrar la ocurrencia del evento");
			return "redirect:/views/evento/ocurrencia";
		}
		
		atributos.addFlashAttribute("success", "Evento registrado exitosamente!");
		return "redirect:/views/evento/ocurrencia";
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Evento>> listarEventos(){
		
		return ResponseEntity.ok(eventoServ.obtenerActivos());
		
	}
	
	@GetMapping("/filtrar")
	public ResponseEntity<List<Evento>> listarEventos(
			@RequestParam(name = "date_range") String date_range,
			@RequestParam(name = "idNotificante", required = false) int idNotificante,
			@RequestParam(name = "idGuardia", required = false) int idGuardia){
		
		try {
			
			if (date_range.indexOf(" - ") != -1){
				String[] parts = date_range.split(" - ");
				
				return ResponseEntity.ok(eventoServ.filtrarEventos(parts[0], parts[1], idGuardia, idNotificante));				 
			}else {
				
				return ResponseEntity.ok(eventoServ.filtrarEventos(null, null, idGuardia, idNotificante));		
			}			
			
		}catch (ParseException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}

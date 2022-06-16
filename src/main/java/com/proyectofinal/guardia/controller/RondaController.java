package com.proyectofinal.guardia.controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectofinal.guardia.service.RondaService;

@Controller
@RequestMapping("/views/ronda")
public class RondaController {

	@Autowired
	private RondaService rondaServ;

	@GetMapping
	public String nuevoRegistro(Model model) {

		model.addAttribute("listaRondas", rondaServ.listarRondasHoy());

		return "/views/rondas/RegistrarAcontecimientoDeRonda";
	}

	@GetMapping("/registros")
	public String verRegistrosDeAcotecimientos(Model model) {

		return "/views/rondas/VerRegistrosDeRondas";
	}

	@PostMapping("/crear")
	public String guardar(@RequestParam(name = "ronda") String ronda, @RequestParam(name = "desc") String desc,
			@RequestParam(name = "planta") String planta, RedirectAttributes atributos) {

		try {
			
			rondaServ.crearRonda(new Date(), ronda, desc, planta);
			
		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se puedo registrar la ronda.");
			return "redirect:/views/ronda";
		}

		atributos.addFlashAttribute("success", "Ronda registrada exitosamente!");
		return "redirect:/views/ronda";
	}
}

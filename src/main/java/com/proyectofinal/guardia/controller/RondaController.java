package com.proyectofinal.guardia.controller;


import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectofinal.guardia.domain.Ronda;
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

		model.addAttribute("listaRondas", rondaServ.listarRondas());

		return "/views/rondas/VerRegistrosDeRondas";
	}

	@PostMapping("/crear")
	public String guardar(@RequestParam(name = "ronda") String ronda, @RequestParam(name = "desc") String desc,
			@RequestParam(name = "planta") int planta, RedirectAttributes atributos) {

		try {

			String plantaAux = "No definida";

			if (planta == 1)
				plantaAux = "Av. Facundo Zuviría 4740 - Planta I";
			if (planta == 2)
				plantaAux = "Av. Peñaloza 5750 - Planta II";

			rondaServ.crearRonda(new Date(), ronda, desc, plantaAux);

		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se puedo registrar la ronda.");
			return "redirect:/views/ronda";
		}

		atributos.addFlashAttribute("success", "Ronda registrada exitosamente!");
		return "redirect:/views/ronda";
	}

	@GetMapping("/cantidad-rondas-diarias")
	public ResponseEntity<Map<String, ?>> cantidadRondasDiarias() {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("cant_rondas", rondaServ.listarRondasHoy().size());

		return ResponseEntity.ok(map);
	}

	@GetMapping("/filtrar")
	public ResponseEntity<List<Ronda>> filtrar(@RequestParam(name = "date_range") String date_range,
			@RequestParam(name = "idUsuario", required = false) int idUsuario) {
		
		try {
			if (date_range.indexOf(" - ") != -1) {
				String[] parts = date_range.split(" - ");	
				
				return ResponseEntity.ok(rondaServ.filtrarRondas(parts[0], parts[1], idUsuario));				 
			}else {
				return ResponseEntity.ok(rondaServ.filtrarRondas(null, null, idUsuario));
			}
		}catch (ParseException e) {
			return ResponseEntity.badRequest().build();
		}	
		
	}
}

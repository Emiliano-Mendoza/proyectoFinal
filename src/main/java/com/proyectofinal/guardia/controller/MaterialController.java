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

import com.proyectofinal.guardia.domain.Material;
import com.proyectofinal.guardia.service.MaterialService;

@Controller
@RequestMapping("/views/material")
public class MaterialController {

	@Autowired
	private MaterialService materialServ;

	@GetMapping("/administrar")
	public String administrarMaterial(Model model) {

		model.addAttribute("listaMateriales", materialServ.obtenerTodos());
		model.addAttribute("materialAux", new Material());

		return "/views/material/AdministrarMaterial";
	}

	@PostMapping("/crear")
	public String crearMaterial(@Valid @ModelAttribute("materialAux") Material material, BindingResult result,
			RedirectAttributes atributos) {
		
		if (result.hasErrors()) {

			atributos.addFlashAttribute("error", "Datos inválidos. No se pudo crear el material.");
			return "redirect:/views/material/administrar";

		} else {

			try {
				materialServ.crearMaterial(material);
			} catch (Exception e) {
				atributos.addFlashAttribute("error", "No se pudo crear el material");
				return "redirect:/views/material/administrar";
			}
		}

		atributos.addFlashAttribute("success", "Material creado exitosamente!");
		return "redirect:/views/material/administrar";
	}
	
	@PostMapping("/editar")
	public String editarMaterial(@Valid @ModelAttribute("materialAux") Material material, BindingResult result,
			RedirectAttributes atributos) {
		
		if (result.hasErrors()) {

			atributos.addFlashAttribute("error", "Datos inválidos. No se pudo editar el material.");
			return "redirect:/views/material/administrar";

		} else {

			try {
				materialServ.editarMaterial(material);
			} catch (Exception e) {
				atributos.addFlashAttribute("error", "No se pudo editar el material");
				return "redirect:/views/material/administrar";
			}
		}

		atributos.addFlashAttribute("success", "Material editado exitosamente!");
		return "redirect:/views/material/administrar";
	}

	@GetMapping
	public ResponseEntity<List<Material>> listarMaterialesDisponibles() {

		return ResponseEntity.ok(materialServ.obtenerDisponibles());
	}
}

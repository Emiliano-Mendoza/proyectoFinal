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

import com.proyectofinal.guardia.domain.Proveedor;
import com.proyectofinal.guardia.service.ProveedorService;

@Controller
@RequestMapping("/views/proveedor")
public class ProveedorController {

	@Autowired
	private ProveedorService provServ;

	@GetMapping("/administrar")
	public String administrarProveedor(Model model) {

		List<Proveedor> listaProveedores = provServ.obtenerTodos();

		model.addAttribute("listaProveedores", listaProveedores);
		model.addAttribute("proveedorAux", new Proveedor());

		return "/views/proveedor/AdministrarProveedor";
	}

		
	@PostMapping("/crear")
	public String crearProveedor(@Valid @ModelAttribute("proveedorAux") Proveedor proveedor, BindingResult result, RedirectAttributes atributos) {
		
		if (result.hasErrors()) {
			atributos.addFlashAttribute("error", "Datos incompletos. No se pudo crear el proveedor.");
			return "redirect:/views/proveedor/administrar";
		}
		
		try {

			provServ.crearProveedor(proveedor);

		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo crear el proveedor");
			return "redirect:/views/proveedor/administrar";
		}

		atributos.addFlashAttribute("success", "Proveedor creado exitosamente!");
		return "redirect:/views/proveedor/administrar";
	}
	
	
	@PostMapping("/editar")
	public String editarProveedor(@Valid @ModelAttribute("proveedorAux") Proveedor proveedor, BindingResult result, RedirectAttributes atributos) {
		
		if (result.hasErrors()) {
			atributos.addFlashAttribute("error", "Datos incompletos. No se pudo editar el proveedor.");
			return "redirect:/views/proveedor/administrar";
		}
		
		try {
			
			provServ.editarProveedor(proveedor);

		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo editar el proveedor");
			return "redirect:/views/proveedor/administrar";
		}

		atributos.addFlashAttribute("success", "Proveedor editado exitosamente!");
		return "redirect:/views/proveedor/administrar";
	}
	
	
	@GetMapping
	public ResponseEntity<List<Proveedor>> listarProveedorDisponibles(){
		
		return ResponseEntity.ok(provServ.obtenerTodos());
	}
}

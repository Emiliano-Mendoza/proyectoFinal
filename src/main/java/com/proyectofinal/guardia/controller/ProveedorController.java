package com.proyectofinal.guardia.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

		return "/views/proveedor/AdministrarProveedor";
	}

	@PostMapping("/crear")
	public String crearProveedor(@RequestParam(name = "nombreProveedor") String nombreProveedor,
			@RequestParam(name = "descripcion") String descripcion, @RequestParam(name = "activo") int activo,
			Model model, RedirectAttributes atributos) {

		try {
			provServ.crearProveedor(nombreProveedor, descripcion, activo == 1 ? true : false);

		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo crear el proveedor");
		}

		atributos.addFlashAttribute("success", "Proveedor creado exitosamente!");

		return "redirect:/views/proveedor/administrar";
	}

	@PostMapping("/editar")
	public String editarProveedor(@RequestParam(name = "idProveedor") int idProveedor,
			@RequestParam(name = "nombreProveedor") String nombreProveedor,
			@RequestParam(name = "descripcion") String descripcion, @RequestParam(name = "activo") int activo,
			Model model, RedirectAttributes atributos) {

		try {
			provServ.editarProveedor(idProveedor, nombreProveedor, descripcion, activo == 1 ? true : false);			
			
		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo editar el proveedor");
		}

		atributos.addFlashAttribute("success", "Proveedor editado exitosamente!");

		return "redirect:/views/proveedor/administrar";
	}
}

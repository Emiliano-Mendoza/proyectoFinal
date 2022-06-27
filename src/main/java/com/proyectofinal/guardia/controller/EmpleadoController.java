package com.proyectofinal.guardia.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectofinal.guardia.domain.Empleado;
import com.proyectofinal.guardia.service.EmpleadoService;

@Controller
@RequestMapping("/views/empleado")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoServ;
	

	@GetMapping("/administrar")
	public String administrarEmpleados(Model model) {

		List<Empleado> listaEmpleados = empleadoServ.obtenerTodos();

		model.addAttribute("empleado", new Empleado());
		model.addAttribute("listaEmpleados", listaEmpleados);

		return "/views/empleados/AdministrarEmpleado";
	}

	@PostMapping("/crear")
	public String crearEmpleado(@Valid @ModelAttribute Empleado empleado, BindingResult result, Model model,
			@RequestParam(name = "file") MultipartFile imagen,
			RedirectAttributes atributos) {
		
		if(empleadoServ.validarDatos(empleado)) {
			result.rejectValue("nroLegajo", "error.empleado", "Número de legajo existente");
		}
				
		if (result.hasErrors()) {
			
			atributos.addFlashAttribute("error", "Datos inválidos. No se pudo crear el empleado.");
			return "redirect:/views/empleado/administrar";

		} else {

			if (!imagen.isEmpty()) {
				String rutaAbsoluta = "C://Guardia//Empleados//recursos";

				try {
					byte[] bytesImg = imagen.getBytes();
					Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
					Files.write(rutaCompleta, bytesImg);

					empleado.setImagen(imagen.getOriginalFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				empleado.setImagen("Sin imagen");
			}
			
			empleado.setEnPlanta(false);
						
			try {
				empleadoServ.crearEmpleado(empleado);
			} catch (Exception e) {
				atributos.addFlashAttribute("error", "No se pudo crear el empleado");
				return "redirect:/views/empleado/administrar";
			}
			
						
			atributos.addFlashAttribute("success", "Empleado creado exitosamente!");
			return "redirect:/views/empleado/administrar";

		}

	}
	
	
	@PostMapping("/editar")
	public String editarEmpleado(@Valid @ModelAttribute Empleado empleado, BindingResult result, Model model,
			@RequestParam(name = "file") MultipartFile imagen,
			RedirectAttributes atributos) {
		
		if(!empleadoServ.validarDatos(empleado)) {
			result.rejectValue("nroLegajo", "error.empleado", "Empleado no encontrado.");
		}
		
		if (result.hasErrors()) {

			atributos.addFlashAttribute("error", "Datos inválidos. No se pudo editar el empleado.");
			return "redirect:/views/empleado/administrar";

		}else {

			if (!imagen.isEmpty()) {
				String rutaAbsoluta = "C://Guardia//Empleados//recursos";

				try {
					byte[] bytesImg = imagen.getBytes();
					Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
					Files.write(rutaCompleta, bytesImg);

					empleado.setImagen(imagen.getOriginalFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
						
			try {
				empleadoServ.editarEmpleado(empleado);
			} catch (Exception e) {
				atributos.addFlashAttribute("error", "No se pudo editar el empleado");
				return "redirect:/views/empleado/administrar";
			}
			
						
			atributos.addFlashAttribute("success", "Empleado editado exitosamente!");
			return "redirect:/views/empleado/administrar";

		}		
		
	}
	
	@GetMapping
	public ResponseEntity<List<Empleado>> listarEmpleadosDisponibles(){
		
		return ResponseEntity.ok(empleadoServ.obtenerTodos());
	}
}

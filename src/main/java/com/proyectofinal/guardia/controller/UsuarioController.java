package com.proyectofinal.guardia.controller;

import java.util.List;

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
import com.proyectofinal.guardia.domain.Usuario;
import com.proyectofinal.guardia.service.UsuarioService;

@Controller
@RequestMapping("/views/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServ;

	@GetMapping("/administrar")
	public String administrarUsuario(Model model) {

		model.addAttribute("listaUsuarios", usuarioServ.getAllUsuario());
		model.addAttribute("listaRoles", usuarioServ.obtenerRoles());

		return "/views/usuario/AdministrarUsuario";
	}

	@PostMapping("/crear")
	public String crearUsuario(@RequestParam(name = "username") String username,
			@RequestParam(name = "contraseña") String contraseña, @RequestParam(name = "nombre") String nombre,
			@RequestParam(name = "apellido") String apellido, @RequestParam(name = "activo") int activo,
			@RequestParam(name = "roles") List<Integer> roles, RedirectAttributes atributos, Model model) {

		// verifico si ya existe el username
		if (usuarioServ.findByUsuario(username) != null) {
			
			atributos.addFlashAttribute("error", "Usuario existente. No se pudo crear el nuevo usuario.");
			return "redirect:/views/usuario/administrar";
		}

		if (username != null && contraseña != null && !roles.isEmpty() && nombre != null && apellido != null
				&& username.length() > 0 && contraseña.length() > 5 && nombre.length() > 0 && apellido.length() > 0) {

			try {
				
				usuarioServ.crearUsuario(username, contraseña, nombre, apellido, activo == 1 ? true : false, roles);

			} catch (Exception e) {

				atributos.addFlashAttribute("error", "No se pudo crear el nuevo usuario");
				return "redirect:/views/usuario/administrar";
			}
		} else {

			atributos.addFlashAttribute("error", "Datos incompletos. No se pudo crear el nuevo usuario");
			return "redirect:/views/usuario/administrar";
		}

		atributos.addFlashAttribute("success", "Usuario creado exitosamente!");
		return "redirect:/views/usuario/administrar";
	}

	@PostMapping("/editar")
	public String editarUsuario(@RequestParam(name = "idUsuario") int idUsuario,
			@RequestParam(name = "contraseña", required = false) String contraseña,
			@RequestParam(name = "nombre") String nombre, @RequestParam(name = "apellido") String apellido,
			@RequestParam(name = "activo") int activo, @RequestParam(name = "roles") List<Integer> roles,
			RedirectAttributes atributos, Model model) {
		
		
		// verifico si existe el usuario
		if (usuarioServ.findById(idUsuario) == null) {
			
			atributos.addFlashAttribute("error", "Usuario inexistente. No se pudo editar usuario.");
			return "redirect:/views/usuario/administrar";
		}

		if (!roles.isEmpty() && nombre != null && apellido != null && nombre.length() > 0 && apellido.length() > 0) {

			try {
				
				usuarioServ.editarUsuario(idUsuario, contraseña, nombre, apellido, activo == 1 ? true : false, roles);
				
			} catch (Exception e) {

				atributos.addFlashAttribute("error", "No se pudo editar el usuario");
				return "redirect:/views/usuario/administrar";
			}
		} else {


			atributos.addFlashAttribute("error", "Datos incompletos. No se pudo editar el usuario");
			return "redirect:/views/usuario/administrar";
		}

		atributos.addFlashAttribute("success", "Usuario editado exitosamente!");
		return "redirect:/views/usuario/administrar";
	}
	
	@GetMapping("/usuarios-activos")
	public ResponseEntity<List<Usuario>> obtenerUsuariosActivos() {

		return ResponseEntity.ok(usuarioServ.obtenerUsuarioActivos());
	}
}

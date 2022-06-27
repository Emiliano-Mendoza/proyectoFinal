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
		model.addAttribute("usuario", new Usuario());

		return "/views/usuario/AdministrarUsuario2";
	}
	
	
	@GetMapping("/usuarios-activos")
	public ResponseEntity<List<Usuario>> obtenerUsuariosActivos() {

		return ResponseEntity.ok(usuarioServ.obtenerUsuarioActivos());
	}

	@PostMapping("/crear")
	public String crearUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result,
			RedirectAttributes atributos) {

		if (result.hasErrors()) {

			atributos.addFlashAttribute("error", "Datos incompletos. No se pudo crear el nuevo usuario.");
			return "redirect:/views/usuario/administrar";
		}

		try {

			usuarioServ.crearUsuario(usuario);

		} catch (Exception e) {			
			atributos.addFlashAttribute("error", "No se pudo crear el usuario. El username ya está en uso.");
			return "redirect:/views/usuario/administrar";
		}

		atributos.addFlashAttribute("success", "Usuario creado exitosamente!");
		return "redirect:/views/usuario/administrar";
	}

	@PostMapping("/editar")
	public String editarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult result,
			RedirectAttributes atributos) {


		if (result.hasErrors() && !(result.hasFieldErrors("contraseña") && result.getErrorCount() == 1)) {

			atributos.addFlashAttribute("error", "Datos incompletos. No se pudo editar el nuevo usuario.");
			return "redirect:/views/usuario/administrar";
		}

		try {

			usuarioServ.editarUsuario(usuario);

		} catch (Exception e) {
			System.out.println(e);
			atributos.addFlashAttribute("error", "No se pudo editar el usuario.");
			return "redirect:/views/usuario/administrar";
		}

		atributos.addFlashAttribute("success", "Usuario creado exitosamente!");
		return "redirect:/views/usuario/administrar";
	}

}

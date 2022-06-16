package com.proyectofinal.guardia.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AutenticacionController {

	
	@GetMapping({"/home", "/index","/"})
	public String index(){
		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Tu usuario y contraseña son inválidos.");

        if (logout != null)
            model.addAttribute("logoutMsg", "Has cerrado sesión correctamente.");

        return "login";
    }
	
}

package com.proyectofinal.guardia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/ingresos-proveedor")
public class IngresosProveedorController {
	
	
	@GetMapping
	public String listarProveedores(Model model) {
						
		return "/views/ingresoProveedor/RegistrarIngresoProveedor";
	}
	
	@GetMapping("/egreso")
	public String listarIngresosDeProveedores(Model model) {
						
		return "/views/ingresoProveedor/RegistrarEgresoProveedor";
	}
	
}

package com.proyectofinal.guardia.controller;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectofinal.guardia.domain.Asistencia;
import com.proyectofinal.guardia.service.AsistenciaService;
import com.proyectofinal.guardia.service.EmpleadoService;

@Controller
@RequestMapping("/views/ingresos-empleado")
public class IngresosEmpleadosController {
	
	@Autowired
	private EmpleadoService empleadoServ;
	
	@Autowired
	private AsistenciaService asisServ;
	
	@GetMapping
	public String listarEmpleados(Model model) {
		
		model.addAttribute("listaEmpleados", empleadoServ.obtenerDisponibles().stream().filter(e -> e.getEnPlanta() != null && !e.getEnPlanta()).collect(Collectors.toList()));
		
		return "/views/asistencias/RegistrarIngresoEmpleado";
	}
	
	@GetMapping("/egreso")
	public String listarAsistenciasParaEgreso(Model model) {
		
		model.addAttribute("listaAsistencias", asisServ.listarAsistenciasSinEgreso());
		
		return "/views/asistencias/RegistrarEgresoEmpleado";
	}
	
	@GetMapping("/registros")
	public String listarAsistenciasYTransitos(Model model) {
						
		return "/views/asistencias/VerRegistrosDeIngresos";
	}
	
	
	@PostMapping("/ingreso/{nroLegajo}")
	public String crearAsistencia(@PathVariable("nroLegajo") int nroLegajo, RedirectAttributes atributos) {
		
		try {
			Date ingreso = new Date();			
			asisServ.crearAsistencia(empleadoServ.buscarEmpleado(nroLegajo).orElseThrow(), ingreso, "Planta I - Av. Facundo");
			empleadoServ.marcarEmpleadoEnPlanta(nroLegajo);
			
		}catch(Exception e){
			atributos.addFlashAttribute("error", "No se pudo registrar el ingreso.");
		}
		
		atributos.addFlashAttribute("success", "Ingreso registrado exitosamente.");
		
		return "redirect:/views/ingresos-empleado";
	}
	
	@PostMapping("/egreso/{idAsistencia}")
	public String egresoAsistencia(@PathVariable("idAsistencia") int idAsistencia, RedirectAttributes atributos) {
		
		try {
			Date egreso = new Date();			
			Asistencia asis = asisServ.egresoAsistencia(idAsistencia, egreso);
			empleadoServ.marcarEmpleadoEgresadoDePlanta(asis.getEmpleado().getNroLegajo());
			
		}catch(Exception e){
			atributos.addFlashAttribute("error", "No se pudo registrar el egreso.");
		}
		
		atributos.addFlashAttribute("success", "Egreso registrado exitosamente.");
		
		return "redirect:/views/ingresos-empleado/egreso";
	}
	
}

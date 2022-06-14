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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectofinal.guardia.domain.Asistencia;
import com.proyectofinal.guardia.domain.Transito;
import com.proyectofinal.guardia.service.AsistenciaService;
import com.proyectofinal.guardia.service.EmpleadoService;
import com.proyectofinal.guardia.service.TransitoService;
import com.proyectofinal.guardia.service.VehiculoService;

@Controller
@RequestMapping("/views/ingresos-empleado")
public class IngresosEmpleadosController {

	@Autowired
	private EmpleadoService empleadoServ;

	@Autowired
	private AsistenciaService asisServ;
	
	@Autowired
	private TransitoService transitoServ;
	
	@Autowired
	private VehiculoService vehiculoServ;

	@GetMapping
	public String listarEmpleados(Model model) {

		model.addAttribute("listaEmpleados", empleadoServ.obtenerDisponibles().stream()
				.filter(e -> e.getEnPlanta() != null && !e.getEnPlanta()).collect(Collectors.toList()));

		return "/views/asistencias/RegistrarIngresoEmpleado";
	}

	@GetMapping("/egreso")
	public String listarAsistenciasParaEgreso(Model model) {

		model.addAttribute("listaAsistencias", asisServ.listarAsistenciasSinEgresoSinTransito());
		model.addAttribute("listaTransitos", transitoServ.obtenerTransitosActivos());

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
			asisServ.crearAsistencia(empleadoServ.buscarEmpleado(nroLegajo).orElseThrow(), ingreso,
					"Planta I - Av. Facundo");
			empleadoServ.marcarEmpleadoEnPlanta(nroLegajo);

		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo registrar el ingreso.");
			return "redirect:/views/ingresos-empleado";
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

		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo registrar el egreso.");
			return "redirect:/views/ingresos-empleado/egreso";
		}

		atributos.addFlashAttribute("success", "Egreso registrado exitosamente.");

		return "redirect:/views/ingresos-empleado/egreso";
	}

	@PostMapping("/egreso/transito/{idAsistencia}")
	public String registrarEgresoTransitorio(@PathVariable("idAsistencia") int idAsistencia,
			@RequestParam(name = "vehiculo") int idVehiculo, @RequestParam(name = "comentario") String comentario, RedirectAttributes atributos) {

		try {
			Date egresoTransitorio = new Date();
			
			if(idVehiculo != -1) {
				transitoServ.crearTransito(idAsistencia, egresoTransitorio, vehiculoServ.obtenerVehiculo(idVehiculo).orElseThrow());
			}else {
				transitoServ.crearTransito(idAsistencia, egresoTransitorio, null);
			}
						
			asisServ.marcarAsistenciaEnTransito(idAsistencia);
			
		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo registrar el egreso transitorio.");
			return "redirect:/views/ingresos-empleado/egreso";
		}

		atributos.addFlashAttribute("success", "Egreso transitorio registrado exitosamente.");

		return "redirect:/views/ingresos-empleado/egreso";
	}
	
	@PostMapping("/egreso/transito/reingreso/{idTransito}")
	public String registrarReingresoDeTransito(@PathVariable("idTransito") int idTransito,
			@RequestParam(name = "vehiculo") int idVehiculo, @RequestParam(name = "comentario") String comentario, RedirectAttributes atributos) {

		try {
			Date reingreso = new Date();
			Transito transito;
			
			if(idVehiculo != -1) {
				transito = transitoServ.reingresoTransito(idTransito, reingreso, vehiculoServ.obtenerVehiculo(idVehiculo).orElseThrow());
			}else {
				transito = transitoServ.reingresoTransito(idTransito, reingreso, null);
			}
			
			asisServ.removerMarcarEnTransito(transito.getAsistencia().getIdAsistencia());
			
		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo registrar el reingreso transitorio.");
			return "redirect:/views/ingresos-empleado/egreso";
		}

		atributos.addFlashAttribute("success", "Reingreso registrado exitosamente.");

		return "redirect:/views/ingresos-empleado/egreso";
	}
}

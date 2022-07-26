package com.proyectofinal.guardia.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public String paginaNuevoRegistroIngreso(Model model) {
		
		model.addAttribute("listaEmpleados", empleadoServ.obtenerDisponibles().stream()
				.filter(e -> e.getEnPlanta() != null && !e.getEnPlanta()).collect(Collectors.toList()));

		return "/views/asistencias/RegistrarIngresoEmpleado";
	}

	@GetMapping("/egreso")
	public String paginaNuevoRegistroEgreso(Model model) {

		model.addAttribute("listaAsistencias", asisServ.listarAsistenciasSinEgresoSinTransito());
		model.addAttribute("listaTransitos", transitoServ.obtenerTransitosActivos());

		return "/views/asistencias/RegistrarEgresoEmpleado";
	}

	@GetMapping("/registros")
	public String listarAsistenciasYTransitos(Model model) {

		return "/views/asistencias/VerRegistrosDeIngresos";
	}

	@PostMapping("/ingreso/{nroLegajo}")
	public String crearAsistencia(@PathVariable("nroLegajo") int nroLegajo, @RequestParam(name = "planta") int planta, RedirectAttributes atributos) {

		try {
			Date ingreso = new Date();
			String plantaAux = "No definida";
			
			if(planta == 1) plantaAux = "Av. Facundo Zuviría 4740 - Planta I";
			if(planta == 2) plantaAux = "Av. Peñaloza 5750 - Planta II";
			
			asisServ.crearAsistencia(empleadoServ.buscarEmpleado(nroLegajo).orElseThrow(), ingreso,
					plantaAux);
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
				transitoServ.crearTransito(idAsistencia, egresoTransitorio, vehiculoServ.obtenerVehiculo(idVehiculo).orElseThrow(), comentario);
			}else {
				transitoServ.crearTransito(idAsistencia, egresoTransitorio, null, comentario);
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
				transito = transitoServ.reingresoTransito(idTransito, reingreso, vehiculoServ.obtenerVehiculo(idVehiculo).orElseThrow(), comentario);
			}else {
				transito = transitoServ.reingresoTransito(idTransito, reingreso, null, comentario);
			}
			
			asisServ.removerMarcarEnTransito(transito.getAsistencia().getIdAsistencia());
			
		} catch (Exception e) {
			atributos.addFlashAttribute("error", "No se pudo registrar el reingreso transitorio.");
			return "redirect:/views/ingresos-empleado/egreso";
		}

		atributos.addFlashAttribute("success", "Reingreso registrado exitosamente.");

		return "redirect:/views/ingresos-empleado/egreso";
	}
	
	
	@GetMapping("/cantidad-ingresados")
	public ResponseEntity<Map<String,?>> ingresosActivos(Model model) {
		
		 Map<String, Object> map = new HashMap<String, Object>();
		 
		 map.put("cant_empleados_ingresados", asisServ.listarAsistenciasSinEgreso().size());
		
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/cantidad-transitos")
	public ResponseEntity<Map<String,?>> transitosActivos(Model model) {
		
		 Map<String, Object> map = new HashMap<String, Object>();
		 
		 map.put("cant_empleados_transito", transitoServ.obtenerTransitosActivos().size());
		
		return ResponseEntity.ok(map);
	}
	
	
	@GetMapping("/listar-ingresos")
	public ResponseEntity<List<Asistencia>> listarAsistencias() {
							
		
		return ResponseEntity.ok(asisServ.listarAsistencias());
	}
	
	@GetMapping("/listar-transitos")
	public ResponseEntity<List<Transito>> listarTransitos() {
							
		
		return ResponseEntity.ok(transitoServ.obtenerTransitos());
	}
	

	@GetMapping("/listar-ingresos/filtrar")
	public ResponseEntity<List<Asistencia>> filtrarAsistencias(@RequestParam(name = "date_range") String date_range,
			@RequestParam(name = "nroLegajo", required = false) int nroLegajo,
			@RequestParam(name = "idUsuario", required = false) int idUsuario) {
		try {
			if (date_range.indexOf(" - ") != -1) {
				
				String[] parts = date_range.split(" - ");							
				return ResponseEntity.ok(asisServ.filtrarAsistencias(parts[0], parts[1], nroLegajo, idUsuario));	
				
			}else {
				return ResponseEntity.ok(asisServ.filtrarAsistencias(null, null, nroLegajo, idUsuario));
			}
			
		} catch (ParseException e) {
			return ResponseEntity.badRequest().build();
		}

	}
	

	@GetMapping("/listar-transitos/filtrar")
	public ResponseEntity<List<Transito>> filtrarTransitos(@RequestParam(name = "date_range") String date_range,
			@RequestParam(name = "nroLegajo", required = false) int nroLegajo,
			@RequestParam(name = "idUsuario", required = false) int idUsuario,
			@RequestParam(name = "idVehiculo", required = false) int idVehiculo) {
		
		try {
			if (date_range.indexOf(" - ") != -1) {
				String[] parts = date_range.split(" - ");			
				return ResponseEntity.ok(transitoServ.filtrarTransitos(parts[0], parts[1], nroLegajo, idUsuario, idVehiculo));
			}else {
				return ResponseEntity.ok(transitoServ.filtrarTransitos(null, null, nroLegajo, idUsuario, idVehiculo));
			}
			
		} catch (ParseException e) {
			return ResponseEntity.badRequest().build();
		}

	}
}

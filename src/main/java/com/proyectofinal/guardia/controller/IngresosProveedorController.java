package com.proyectofinal.guardia.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.proyectofinal.guardia.domain.IngresoProveedor;
import com.proyectofinal.guardia.domain.Proveedor;
import com.proyectofinal.guardia.service.IngresoProveedorService;
import com.proyectofinal.guardia.service.ProveedorService;

@Controller
@RequestMapping("/views/ingresos-proveedor")
public class IngresosProveedorController {

	@Autowired
	private ProveedorService provServ;

	@Autowired
	private IngresoProveedorService ingresoServ;

	@GetMapping
	public String paginaNuevoRegistroIngreso(Model model) {

		List<Proveedor> listaProveedores = provServ.obtenerDisponibles();

		model.addAttribute("listaProveedores", listaProveedores);

		return "/views/ingresoProveedor/RegistrarIngresoProveedor";
	}

	@GetMapping("/egreso")
	public String paginaNuevoRegistroEgreso(Model model) {
		
		List<IngresoProveedor> listaIngresos = ingresoServ.obtenerIngresosActivos();
		
		model.addAttribute("listaIngresos", listaIngresos);
		
		return "/views/ingresoProveedor/RegistrarEgresoProveedor";
	}

	@PostMapping("/ingreso/{idProveedor}")
	public String registrarIngresoDeProveedor(@PathVariable("idProveedor") int idProveedor,
			@RequestParam(name = "planta") int planta,
			@RequestParam(name = "chofer") String chofer,
			@RequestParam(name = "patenteVehiculo") String patenteVehiculo, RedirectAttributes atributos) {
				
		try {
			Date ingreso = new Date();
			
			String plantaAux = "No definida";
			
			if(planta == 1) plantaAux = "Av. Facundo Zuviría 4740 - Planta I";
			if(planta == 2) plantaAux = "Av. Peñaloza 5750 - Planta II";
			
			ingresoServ.crearIngresoProveedor(idProveedor, ingreso, plantaAux, chofer, patenteVehiculo);
			
		}catch(Exception e){
			atributos.addFlashAttribute("error", "No se pudo registrar el ingreso.");
		}
		
		atributos.addFlashAttribute("success", "Ingreso registrado exitosamente.");
		
		return "redirect:/views/ingresos-proveedor";
	}
	
	@PostMapping("/egreso/{idIngreso}")
	public String registrarEgresoDeProveedor(@PathVariable("idIngreso") int idIngreso, RedirectAttributes atributos) {
				
		try {
			Date egreso = new Date();			
			ingresoServ.egresoProveedor(idIngreso, egreso);
			
		}catch(Exception e){
			atributos.addFlashAttribute("error", "No se pudo registrar el egreso.");
		}
		
		atributos.addFlashAttribute("success", "Egreso registrado exitosamente.");
		
		return "redirect:/views/ingresos-proveedor/egreso";
	}
	
	@GetMapping("/cantidad-ingresados")
	public ResponseEntity<Map<String,?>> ingresosActivos(Model model) {
		
		 Map<String, Object> map = new HashMap<String, Object>();
		 
		 map.put("cant_proveedores_ingresados", ingresoServ.obtenerIngresosActivos().size());
		
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/listar-ingresos")
	public ResponseEntity<List<IngresoProveedor>> listarAsistencias() {
							
		
		return ResponseEntity.ok(ingresoServ.listarIngresos());
	}
	
	@GetMapping("/listar-ingresos/filtrar")
	public ResponseEntity<List<IngresoProveedor>> filtrarIngresos(@RequestParam(name = "date_range") String date_range,
			@RequestParam(name = "idProveedor", required = false) int idProveedor,
			@RequestParam(name = "idUsuario", required = false) int idUsuario) {
		
		if (date_range.indexOf(" - ") != -1) {
			String[] parts = date_range.split(" - ");			
			return ResponseEntity.ok(ingresoServ.filtrarIngresos(parts[0], parts[1], idProveedor, idUsuario));
		}
		
		return ResponseEntity.ok(ingresoServ.filtrarIngresos(null, null, idProveedor, idUsuario));
	}
}

package com.proyectofinal.guardia.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.IngresoProveedorJPARepository;
import com.proyectofinal.guardia.dao.ProveedorJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.IngresoProveedor;
import com.proyectofinal.guardia.service.IngresoProveedorService;

@Service
public class IngresoProveedorServiceImpl implements IngresoProveedorService {
	
	@Autowired
	private IngresoProveedorJPARepository ingresoRepo;
	
	@Autowired
	private ProveedorJPARepository proveedorRepo;
	
	@Autowired
	private UsuarioJPARepository usuarioRepo;
	
	@Override
	public IngresoProveedor crearIngresoProveedor(int idProveedor, Date ingreso, String planta, String chofer,
			String patente) {
		
		IngresoProveedor ingresoProveedor = new IngresoProveedor();
		ingresoProveedor.setProveedor(proveedorRepo.getById(idProveedor));
		ingresoProveedor.setIngreso(ingreso);
		ingresoProveedor.setPlanta(planta);
		ingresoProveedor.setNombreChofer(chofer);
		ingresoProveedor.setPatenteVehiculo(patente);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ingresoProveedor.setUsuarioIngreso(usuarioRepo.findByUsername(auth.getName()));
						
		return ingresoRepo.save(ingresoProveedor);
	}

	@Override
	public IngresoProveedor egresoProveedor(int idIngreso, Date egreso) {
		
		IngresoProveedor ingresoProveedor = ingresoRepo.getById(idIngreso);
		ingresoProveedor.setEgreso(egreso);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ingresoProveedor.setUsuarioEgreso(usuarioRepo.findByUsername(auth.getName()));
		
		return ingresoRepo.save(ingresoProveedor);
	}

	@Override
	public List<IngresoProveedor> obtenerIngresos() {
		
		return ingresoRepo.findAll();
	}

	@Override
	public List<IngresoProveedor> obtenerIngresosActivos() {
		
		return ingresoRepo.findAllActivos();
	}

	@Override
	public List<IngresoProveedor> listarIngresos() {
		
		return ingresoRepo.findAll();
	}

	@Override
	public List<IngresoProveedor> filtrarIngresos(String fechaInicio, String fechaFin, int idProveedor, int idUsuario) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicioAux = formatter.parse(fechaInicio != null ? fechaInicio : "01/01/2000");
		Date fechaFinalAux = fechaFin != null ? new Date(formatter.parse(fechaFin).getTime() + (1000 * 60 * 60 * 24)) : new Date(3000,0,1);

		return ingresoRepo.findAll().stream().filter(a -> 
				   (a.getIngreso() != null ?  a.getIngreso().after(fechaInicioAux) : true)
				&& (a.getIngreso() != null ? a.getIngreso().before(fechaFinalAux) : true)
				&& (idUsuario > 0
						? ((a.getUsuarioIngreso() != null && a.getUsuarioIngreso().getIdUsuario() == idUsuario)
								|| (a.getUsuarioEgreso() != null
										&& a.getUsuarioEgreso().getIdUsuario() == idUsuario))
						: true)
				&& (idProveedor > 0 ? (a.getProveedor() != null && a.getProveedor().getIdProveedor() == idProveedor)
						: true))
				.collect(Collectors.toList());
	}

}

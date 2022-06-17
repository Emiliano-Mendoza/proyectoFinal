package com.proyectofinal.guardia.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.AutorizacionJPARepository;
import com.proyectofinal.guardia.dao.EmpleadoJPARepository;
import com.proyectofinal.guardia.dao.MaterialJPARepository;
import com.proyectofinal.guardia.dao.RetiroJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.AutorizacionRetiroMaterial;
import com.proyectofinal.guardia.domain.Empleado;
import com.proyectofinal.guardia.domain.Retiro;
import com.proyectofinal.guardia.service.RetiroMaterialService;

@Service
public class RetiroMaterialServiceImpl implements RetiroMaterialService {
	
	@Autowired
	private RetiroJPARepository retiroRepo;
	
	@Autowired
	private AutorizacionJPARepository autRepo;
	
	@Autowired
	private EmpleadoJPARepository empleadoRepo;
	
	@Autowired
	private MaterialJPARepository materialRepo;
	
	@Autowired
	private UsuarioJPARepository usuarioRepo;
	
	@Override
	public AutorizacionRetiroMaterial crearAutorizacion(int nroLegajo, List<Integer> materiales, String fechaLimite,
			String descripcion) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {			
			AutorizacionRetiroMaterial autorizacion = new AutorizacionRetiroMaterial();
			
			if(nroLegajo > 0) {
				autorizacion.setEmpleado(empleadoRepo.findById(nroLegajo).orElseThrow());
			}else {
				autorizacion.setEmpleado(null);
			}
			
			materiales.forEach((mat)->{autorizacion.getMateriales().add(materialRepo.findById(mat).get());});
						
			autorizacion.setDescripcion(descripcion);
			
			Date fecha = formatter.parse(fechaLimite + " 23:59:59");
			autorizacion.setFechaLimite(fecha);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			autorizacion.setUsuario(usuarioRepo.findByUsername(auth.getName()));
			
			return autRepo.save(autorizacion);
			
		}catch(Exception e){
			
		}
		
		
		return null;
	}

	@Override
	public Retiro registrarRetiro(int idAutorizacion, Date fechaRetiro, String observacion, String planta) {
		
		Retiro retiro = new Retiro();
		retiro.setFechaRetiro(fechaRetiro);
		retiro.setObservacion(observacion);
		retiro.setPlanta(planta);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		retiro.setUsuario(usuarioRepo.findByUsername(auth.getName()));
		
		AutorizacionRetiroMaterial autorizacion = autRepo.findById(idAutorizacion).orElseThrow();
		autorizacion.setRetiro(retiro);
						
		return autRepo.save(autorizacion).getRetiro();
	}

	@Override
	public List<AutorizacionRetiroMaterial> obtenerAutorizacionesActivas() {
		
		return autRepo.findAllByOrderByFechaLimiteAsc().stream().filter(a -> a.getFechaLimite().after(new Date()) && a.getRetiro() == null).collect(Collectors.toList());
	}

	@Override
	public List<AutorizacionRetiroMaterial> obtenerAutorizaciones() {
		// TODO Auto-generated method stub
		return autRepo.findAllByOrderByFechaLimiteAsc();
	}

}

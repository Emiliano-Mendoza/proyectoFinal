package com.proyectofinal.guardia.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.AutorizacionJPARepository;
import com.proyectofinal.guardia.dao.RetiroJPARepository;
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
	
	
	@Override
	public AutorizacionRetiroMaterial crearAsistencia(Empleado empleado, List<Integer> materiales, String fechaLimite,
			String descripcion) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {			
			AutorizacionRetiroMaterial autorizacion = new AutorizacionRetiroMaterial();			
			autorizacion.setEmpleado(empleado);
			autorizacion.setDescripcion(descripcion);
			
			Date fecha = formatter.parse(fechaLimite + " 23:59:59");
			autorizacion.setFechaLimite(fecha);
			
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
		
		AutorizacionRetiroMaterial autorizacion = autRepo.findById(idAutorizacion).orElseThrow();
		autorizacion.setRetiro(retiro);
		autRepo.save(autorizacion);
		
		return retiroRepo.save(retiro);
	}

	@Override
	public List<AutorizacionRetiroMaterial> obtenerAutorizacionesActivas() {
		
		return autRepo.findAll().stream().filter(a -> a.getFechaLimite().after(new Date()) && a.getRetiro() == null).collect(Collectors.toList());
	}

	@Override
	public List<AutorizacionRetiroMaterial> obtenerAutorizaciones() {
		// TODO Auto-generated method stub
		return autRepo.findAll();
	}

}

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


import com.proyectofinal.guardia.dao.RondaJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.Ronda;
import com.proyectofinal.guardia.service.RondaService;

@Service
public class RondaServiceImpl implements RondaService {
	
	@Autowired
	private RondaJPARepository rondaRepo;
	
	@Autowired
	private UsuarioJPARepository usuarioRepo;
	
	@Override
	public Ronda crearRonda(Date fecha, String ronda, String descripcion, String planta) {

		Ronda rondaNueva = new Ronda();
		rondaNueva.setFechaRonda(fecha);
		rondaNueva.setDescripcion(descripcion);
		rondaNueva.setRonda(ronda);
		rondaNueva.setPlanta(planta);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		rondaNueva.setUsuario(usuarioRepo.findByUsername(auth.getName()));
		
		return rondaRepo.save(rondaNueva);
	}

	@Override
	public List<Ronda> listarRondas() {
		
		return rondaRepo.findAll();
	}

	@Override
	public List<Ronda> listarRondasHoy(){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaActual;
		try {
			fechaActual = formatter.parse(formatter.format(new Date()));
												
			return rondaRepo.findAll().stream().filter(r -> r.getFechaRonda().after(fechaActual)).collect(Collectors.toList());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Ronda> filtrarRondas(String fechaInicio, String fechaFin, int idUsuario) {
		
		try {
						
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaInicioAux = formatter.parse(fechaInicio != null ? fechaInicio : "01/01/2000");
			Date fechaFinalAux = fechaFin != null ? new Date(formatter.parse(fechaFin).getTime() + (1000 * 60 * 60 * 24)) : new Date(3000,0,1);
			
			return rondaRepo.findAll().stream().filter(r -> r.getFechaRonda().after(fechaInicioAux) && r.getFechaRonda().before(fechaFinalAux)
					&& ( idUsuario > 0 ? ( r.getUsuario()!= null && r.getUsuario().getIdUsuario() == idUsuario) : true)).collect(Collectors.toList());
						
						
		} catch (ParseException e) {

			e.printStackTrace();
		}
						
		
		return null;
	}

}

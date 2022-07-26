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


import com.proyectofinal.guardia.dao.EventoJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.Evento;
import com.proyectofinal.guardia.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {
	
	@Autowired
	private EventoJPARepository eventoRepo;
	@Autowired
	private UsuarioJPARepository usuarioRepo;
	
	@Override
	public Evento crearEvento(Evento evento, String fecha) throws ParseException {
						
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date fechaInicioAux = formatter.parse(fecha);
		
		evento.setCancelado(false);
		evento.setFechaEvento(fechaInicioAux);
		evento.setOcurrencia(false);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		evento.setNotificador(usuarioRepo.findByUsername(auth.getName()));
					
		return eventoRepo.save(evento);
	}

	@Override
	public Evento editarEvento(Evento evento) {
		
		return null;
	}

	@Override
	public List<Evento> obtenerTodos() {
		
		return eventoRepo.findAll();
	}

	@Override
	public List<Evento> obtenerActivos() {
		
		Date fechaAux = new Date(new Date().getTime() - 3*(1000 * 60 * 60 * 24));
		
		List<Evento> listaEvento = eventoRepo.findAllByOrderByFechaEventoAsc()
				.stream()
				.filter(e -> e.getFechaEvento() != null
						&& e.getFechaEvento().after(fechaAux))
				.collect(Collectors.toList());
		
		return listaEvento;
	}

	@Override
	public List<Evento> obtenerActivosUsuario() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Date fechaAux = new Date(new Date().getTime() - 3*(1000 * 60 * 60 * 24));
				
		List<Evento> listaEvento = eventoRepo.findAllByOrderByFechaEventoAsc()
				.stream()
				.filter(e -> e.getFechaEvento() != null
						&& e.getFechaEvento().after(fechaAux) 
						&& e.getNotificador().getIdUsuario() == usuarioRepo.findByUsername(auth.getName()).getIdUsuario())
				.collect(Collectors.toList());
		
		return listaEvento;
	}

}

package com.proyectofinal.guardia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.proyectofinal.guardia.dao.EventoJPARepository;
import com.proyectofinal.guardia.domain.Evento;
import com.proyectofinal.guardia.service.EventoService;
@Service
public class EventoServiceImpl implements EventoService {
	
	@Autowired
	private EventoJPARepository eventoRepo;
	
	@Override
	public Evento crearEvento(Evento evento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evento editarEvento(Evento evento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> obtenerActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}

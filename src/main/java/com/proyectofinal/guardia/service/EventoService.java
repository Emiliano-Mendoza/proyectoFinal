package com.proyectofinal.guardia.service;

import java.util.List;

import com.proyectofinal.guardia.domain.Evento;

public interface EventoService {
	
	public Evento crearEvento(Evento evento);
	public Evento editarEvento(Evento evento);
	public List<Evento> obtenerTodos();
	public List<Evento> obtenerActivos();
	
}

package com.proyectofinal.guardia.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.proyectofinal.guardia.domain.Evento;

public interface EventoService {
	
	public Evento crearEvento(Evento evento, String fecha) throws ParseException;
	public Evento editarEvento(Evento evento);
	public List<Evento> obtenerTodos();
	public Optional<Evento> obtenerPorId(int idEvento);
	public List<Evento> obtenerActivos();
	public List<Evento> obtenerActivosUsuario();
	
}

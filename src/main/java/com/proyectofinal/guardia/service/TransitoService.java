package com.proyectofinal.guardia.service;

import java.util.Date;
import java.util.List;

import com.proyectofinal.guardia.domain.Transito;

public interface TransitoService {

	public Transito crearTransito(int idAsistencia, Date salida);
	public Transito reingresoTransito(int idTransito, Date reingreso);
	public List<Transito> obtenerTransitos();
	public List<Transito> obtenerTransitosActivos();
			
}

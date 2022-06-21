package com.proyectofinal.guardia.service;

import java.util.Date;
import java.util.List;

import com.proyectofinal.guardia.domain.Transito;
import com.proyectofinal.guardia.domain.Vehiculo;

public interface TransitoService {

	public Transito crearTransito(int idAsistencia, Date salida, Vehiculo vehiculo, String comentario);
	public Transito reingresoTransito(int idTransito, Date reingreso, Vehiculo vehiculo, String comentario);
	public List<Transito> obtenerTransitos();
	public List<Transito> obtenerTransitosActivos();
			
}

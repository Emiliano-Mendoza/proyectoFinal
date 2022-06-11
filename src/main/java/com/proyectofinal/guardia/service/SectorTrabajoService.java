package com.proyectofinal.guardia.service;

import java.util.List;

import com.proyectofinal.guardia.domain.SectorTrabajo;

public interface SectorTrabajoService {
	
	public List<SectorTrabajo> obtenerTodos();
	public List<SectorTrabajo> obtenerDisponibles();
	
}

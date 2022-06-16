package com.proyectofinal.guardia.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.proyectofinal.guardia.domain.Ronda;

public interface RondaService {
	
	public Ronda crearRonda(Date fecha, String ronda, String descripcion, String planta);
	public List<Ronda> listarRondas();
	public List<Ronda> listarRondasHoy();
	
}

package com.proyectofinal.guardia.service;

import java.util.List;

import com.proyectofinal.guardia.domain.Vehiculo;

public interface VehiculoService {

	public Vehiculo crearVehiculo(Vehiculo vehiculo);
	public Vehiculo editarVehiculo(Vehiculo vehiculo);
	public List<Vehiculo> obtenerTodos();
	public List<Vehiculo> obtenerDisponibles();
	
}

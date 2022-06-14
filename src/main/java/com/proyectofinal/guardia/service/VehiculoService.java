package com.proyectofinal.guardia.service;

import java.util.List;
import java.util.Optional;

import com.proyectofinal.guardia.domain.Vehiculo;

public interface VehiculoService {

	public Vehiculo crearVehiculo(Vehiculo vehiculo);
	public Vehiculo editarVehiculo(Vehiculo vehiculo);
	public List<Vehiculo> obtenerTodos();
	public List<Vehiculo> obtenerDisponibles();
	public Optional<Vehiculo> obtenerVehiculo(int idVehiculo);
	
}

package com.proyectofinal.guardia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.VehiculoJPARepository;
import com.proyectofinal.guardia.domain.Vehiculo;
import com.proyectofinal.guardia.service.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService {
	
	@Autowired
	private VehiculoJPARepository vehiculoRepo;
	
	
	@Override
	public Vehiculo crearVehiculo(Vehiculo vehiculo) {
		
		return vehiculoRepo.save(vehiculo);
	}

	@Override
	public Vehiculo editarVehiculo(Vehiculo vehiculo) {
		
		
		return vehiculoRepo.save(vehiculo);
	}

	@Override
	public List<Vehiculo> obtenerTodos() {
		
		return vehiculoRepo.findAll();
	}

	@Override
	public List<Vehiculo> obtenerDisponibles() {

		return vehiculoRepo.findAllDisponibles();
	}

}

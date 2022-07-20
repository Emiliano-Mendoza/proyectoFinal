package com.proyectofinal.guardia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyectofinal.guardia.domain.Vehiculo;

public interface VehiculoJPARepository extends JpaRepository<Vehiculo, Integer> {
		
	@Query(
			value = "SELECT * FROM vehiculo v WHERE v.activo = 1", 
			nativeQuery = true)
	public List<Vehiculo> findAllDisponibles();
}

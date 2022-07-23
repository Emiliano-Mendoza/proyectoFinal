package com.proyectofinal.guardia.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyectofinal.guardia.domain.Empleado;

public interface EmpleadoJPARepository extends JpaRepository<Empleado, Integer> {

	@Query(
			value = "SELECT * FROM empleado e WHERE e.activo = 1 ORDER BY e.apellido", 
			nativeQuery = true)
	public List<Empleado> findAllDisponibles();
	
}

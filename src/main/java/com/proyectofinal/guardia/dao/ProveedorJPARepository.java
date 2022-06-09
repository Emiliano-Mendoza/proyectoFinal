package com.proyectofinal.guardia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.proyectofinal.guardia.domain.Proveedor;

public interface ProveedorJPARepository extends JpaRepository<Proveedor, Integer> {
	
	@Query(
			value = "SELECT * FROM proveedor p WHERE p.activo = true", 
			nativeQuery = true)
	public List<Proveedor> findAllDisponibles();
}

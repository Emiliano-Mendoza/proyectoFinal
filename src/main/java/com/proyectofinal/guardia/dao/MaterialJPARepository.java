package com.proyectofinal.guardia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyectofinal.guardia.domain.Material;

public interface MaterialJPARepository extends JpaRepository<Material, Integer> {
	
	@Query(
			value = "SELECT * FROM material m WHERE m.activo = 1", 
			nativeQuery = true)
	public List<Material> findAllDisponibles();
	
}

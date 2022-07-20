package com.proyectofinal.guardia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyectofinal.guardia.domain.SectorTrabajo;

public interface SectorTrabajoJPARepository extends JpaRepository<SectorTrabajo, Integer> {
	
	@Query(
			value = "SELECT * FROM sector_trabajo s WHERE s.activo = 1", 
			nativeQuery = true)
	public List<SectorTrabajo> findAllActivos();
	
}
